package moe._47saikyo.drm.backend.controller

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.configuration.security.authenticateRequired
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.WalletService
import moe._47saikyo.drm.blockchain.service.RightService
import org.koin.ktor.ext.inject
import kotlin.math.log

/**
 * Right Contract Controller
 *
 * @author 刘一邦
 */
fun Application.chainRightController() {
    val walletService: WalletService by inject()
    val rightService: RightService by inject()

    routing {
        route("/chain/right") {
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
                get {
                    val rightDeployer = call.parameters["deployer"]
                    val rightIndex = call.parameters["index"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        //rightDeployer为空
                        (rightDeployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的部署者")
                            return@get
                        }

                        //rightIndex为空
                        (rightIndex == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }

                        //rightIndex不是数字
                        (rightIndex.toIntOrNull() == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }
                    }

                    val rights = rightService.getRight(loginAddr!!, rightDeployer!!, rightIndex!!.toLong())

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }

                //获取指定钱包所部署的版权合约列表
                get("/deployer") {
                    val rightDeployer = call.parameters["deployer"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        //rightDeployer为空
                        (rightDeployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的部署者")
                            return@get
                        }
                    }

                    val rights = rightService.getRights(loginAddr!!,rightDeployer!!)

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }

                //通过标题模糊搜索版权合约
                get("/search") {
                    val title = call.parameters["title"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        //title为空
                        (title == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的标题")
                            return@get
                        }
                    }

                    val rights = rightService.searchByTitle(loginAddr!!, title!!)

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }
            }
        }
    }
}