package moe._47saikyo.controller

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.configuration.security.authenticateAfterLogin
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.AccountService
import moe._47saikyo.service.UserService
import org.koin.java.KoinJavaComponent

/**
 * BlockChain HTTP Controller
 *
 * @author 刘一邦
 */
fun Application.chainController() {
    val userService: UserService by KoinJavaComponent.inject(UserService::class.java)
    val accountService: AccountService by KoinJavaComponent.inject(AccountService::class.java)

    routing {
        route("/chain") {
            authenticate(Constant.Authentication.NEED_LOGIN) {
                get("/balance") {
                    val addr = call.request.queryParameters["addr"]

                    if (addr == null || !addr.matches(Regex("0x[0-9A-Fa-f]{40}"))) {
                        call.httpRespond(HttpStatus.BAD_REQUEST)
                        return@get
                    }

                    val balance = accountService.getBalance(addr)
                    call.httpRespond(HttpStatus.SUCCESS, balance)
                }

                post("/charge") {
                    val value = call.receive<String>()
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginUser = loginId?.let { id -> userService.getUser(id) }

                    when {
                        //检查登陆用户合法性
                        (loginUser == null) -> {
                            call.httpRespond(HttpStatus.UNAUTHORIZED)
                            return@post
                        }

                        (loginUser.chainAddress == null || loginUser.chainAddress!!.isEmpty()) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "当前账号未开通链上账户")
                            return@post
                        }
                    }

                    when (accountService.chargeFromBank(loginUser!!.chainAddress!!, value)) {
                        true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))

                        false -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                    }
                }
            }
            authenticateAfterLogin(Constant.Authentication.PERMISSION_CREATE_CHAIN_ACCOUNT) {
                post("/new-account") {
                    data class PasswordForm(
                        val password: String,
                        val confirmPassword: String
                    )

                    val form = call.receive<PasswordForm>()
                    val password = form.password
                    val confirmPassword = form.confirmPassword

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginUser = loginId?.let { id -> userService.getUser(id) }

                    when {
                        //两次密码相同
                        (password != confirmPassword) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "两次密码不匹配")
                            return@post
                        }

                        //检查登陆用户合法性
                        (loginUser == null) -> {
                            call.httpRespond(HttpStatus.UNAUTHORIZED)
                            return@post
                        }

                        //单一用户仅能绑定一个链上账号
                        (loginUser.chainAddress != null && loginUser.chainAddress!!.isNotEmpty()) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "该用户已绑定链上账户")
                            return@post
                        }
                    }

                    try {
                        //创建新的链上账户
                        val addr = accountService.newAccountByPersonal(password)

                        if (addr.isEmpty()) {
                            call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                            return@post
                        }

                        //将链上账户地址绑定至用户信息并更新
                        loginUser!!.chainAddress = addr
                        when (userService.updateUser(loginUser)) {
                            true -> call.httpRespond(
                                data = mapOf(
                                    Constant.RespondField.ADDRESS to addr,
                                    Constant.RespondField.SUCCESS to true
                                )
                            )

                            false -> call.httpRespond(
                                data = mapOf(Constant.RespondField.SUCCESS to false)
                            )
                        }
                    } catch (e: Exception) {
                        call.httpRespond(HttpStatus.SERVER_ERROR with (e.message ?: "Unknown Error"))
                    }
                }
            }
        }
    }
}