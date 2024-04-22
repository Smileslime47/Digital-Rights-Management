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
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.blockchain.service.RightService
import org.koin.ktor.ext.inject

/**
 * License Contract Controller
 *
 * @author 刘一邦
 */
fun Application.chainLicenseController() {
    val licenseService: LicenseService by inject()
    val rightService: RightService by inject()
    val walletService: WalletService by inject()

    routing {
        route("/chain/license") {
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
//                get {
//                    val addr = call.parameters["addr"]
//                    val caller = call.parameters["caller"]
//
//
//                    when {
//                        //addr为空
//                        (addr == null) -> {
//                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
//                            return@get
//                        }
//
//                        //caller为空
//                        (caller == null) -> {
//                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的调用者")
//                            return@get
//                        }
//                    }
//
//                    val rights = licenseService.getPureData(caller!!, addr!!)
//
//                    call.httpRespond(data = mapOf(Constant.RespondField.LICENSE to rights))
//                }

                //获取指定钱包所部署的版权合约列表
                get("/by-deployer") {
                    val deployer = call.parameters["deployer"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        //addr为空
                        (deployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                            return@get
                        }
                    }

                    val licenses = licenseService.getLicenses(loginAddr!!, deployer!!)

                    call.httpRespond(data = mapOf(Constant.RespondField.LICENSE to licenses))
                }

                get("/by-right") {
                    val rightDeployer = call.parameters["deployer"]
                    val rightIndex = call.parameters["index"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        //rightDeployer为空
                        (rightDeployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
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

                    val right = rightService.getRight(loginAddr!!, rightDeployer!!, rightIndex!!.toLong())

                    val licenseKeyPairs = right.licenses
//                        .map { licenseService.getPureData(loginAddr, it) }
                        .filter { it.deployer == loginAddr || right.deployer == loginAddr }

                    val licenses = licenseKeyPairs.map {
                        licenseService.getLicense(loginAddr, it.deployer, it.arrayIndex)
                    }

                    call.httpRespond(data = mapOf(Constant.RespondField.LICENSE to licenses))
                }
            }
        }
    }
}