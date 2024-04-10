package moe._47saikyo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import moe._47saikyo.domain.Wallet
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.configuration.security.authenticateRequired
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.AccountService
import moe._47saikyo.service.UserService
import moe._47saikyo.service.WalletService
import moe._47saikyo.utils.CryptoUtils
import org.koin.ktor.ext.inject
import java.util.*

/**
 * BlockChain HTTP Controller
 *
 * @author 刘一邦
 */
fun Application.chainAccountController() {
    val userService: UserService by inject()
    val walletService: WalletService by inject()
    val accountService: AccountService by inject()

    routing {
        route("/chain/account") {
            //获取链上账户余额
            get("/balance") {
                var addr = call.request.queryParameters["addr"]

                if (addr == null || !addr.matches(Regex("(0x)?[0-9A-Fa-f]{40}"))) {
                    call.httpRespond(HttpStatus.BAD_REQUEST)
                    return@get
                }

                if (!addr.startsWith("0x")) {
                    addr = "0x$addr"
                }

                val balance = accountService.getBalance(addr)
                call.httpRespond(data = mapOf(Constant.RespondField.BALANCE to balance))
            }

            //要求登陆
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
                /**
                 * 获取用户链上账户地址
                 * 在不存在链上账户时，返回null
                 * 命令前端提示用户创建链上账户
                 */
                get("/by-user") {
                    val targetIdStr = call.request.queryParameters["id"]
                    val targetId = if (targetIdStr?.matches(Regex("[0-9]+")) == true) targetIdStr.toLong() else null
                    val targetWallet:Wallet? = targetId?.let { id -> walletService.getWallet(id) }

                    call.httpRespond(data = mapOf(Constant.RespondField.ADDRESS to targetWallet?.address))
                }

                //要求登陆并且有链上账户
                authenticateRequired(Constant.Authentication.NEED_BLOCK_ACCOUNT){
                    //从银行充值
                    post("/charge") {
                        data class Form(
                            val value: String
                        )

                        val value = call.receive<Form>().value
                        val loginId =
                            call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                                ?.asLong()
                        val loginWallet = loginId?.let { id -> walletService.getWallet(id) }

                        when (accountService.chargeFromBank(loginWallet!!.address, value)) {
                            true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                            false -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                        }
                    }
                }

                //要求登陆并且有权创建链上账户
                authenticateRequired(Constant.Authentication.PERMISSION_CREATE_CHAIN_ACCOUNT){
                    //创建链上账户
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
                        }

                        try {
                            //创建钱包文件并获取地址
                            val walletFile = accountService.newAccount(password)
                            val walletFileJson = ObjectMapper().writeValueAsString(walletFile)
                            var addr = walletFile.address
                            if (addr.startsWith("0x")) {
                                addr = "0x$addr"
                            }

                            //生成加密密钥和初始化向量
                            val secretKey = CryptoUtils.getSecretKey(password)
                            val iv = CryptoUtils.getRandomByteArray(CryptoUtils.IV_128_BIT)

                            //序列号钱包文件并加密
                            val encryptedWalletFile = CryptoUtils.encrypt(
                                data = walletFileJson.toByteArray(),
                                secretKey = secretKey,
                                iv = iv
                            )

                            //数据进行Base64编码
                            val encoder = Base64.getEncoder()
                            val base64Iv = String(encoder.encode(iv))
                            val base64Wallet = String(encoder.encode(encryptedWalletFile))

                            //将链上账户地址绑定至用户信息并更新
                            val wallet = Wallet(
                                address = addr,
                                walletFile = base64Wallet,
                                cipherIv = base64Iv,
                                userId = loginUser!!.id
                            )
                            when (walletService.insertWallet(wallet) != null) {
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
}