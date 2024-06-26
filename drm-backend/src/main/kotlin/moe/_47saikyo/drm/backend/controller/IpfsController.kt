package moe._47saikyo.drm.backend.controller

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.configuration.security.authenticateRequired
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.fileRespond
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.WalletService
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.blockchain.service.RightService
import moe._47saikyo.drm.ipfs.service.IpfsService
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

/**
 * IPFS Controller
 *
 * @author 刘一邦
 */
fun Application.ipfsController() {
    val logger = LoggerFactory.getLogger("IpfsController")
    val walletService: WalletService by inject()
    val ipfsService: IpfsService by inject()
    val licenseService: LicenseService by inject()
    val rightService: RightService by inject()

    routing {
        route("/ipfs") {
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
                post {
                    val multipartData = call.receiveMultipart()

                    var fileName = ""
                    var fileHash = ""
                    var fileBytes: ByteArray

                    multipartData.forEachPart { part ->
                        if (part is PartData.FileItem) {
                            fileName = part.originalFileName ?: "UnnamedFile"
                            fileBytes = part.streamProvider().readBytes()
                            fileHash = ipfsService.upload(fileBytes)
                        }
                        part.dispose()
                    }

                    call.httpRespond(
                        data = mapOf(
                            Constant.RespondField.NAME to fileName,
                            Constant.RespondField.HASH to fileHash
                        )
                    )
                }

                get("/by-license") {
                    val licenseDeployer = call.parameters["deployer"]
                    val licenseIndex = call.parameters["index"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        (licenseDeployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的部署者")
                            return@get
                        }

                        (licenseIndex == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }

                        (licenseIndex.toIntOrNull() == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }
                    }

                    val targetLicense = licenseService.getLicense(loginAddr!!, licenseDeployer!!, licenseIndex!!.toBigInteger())
                    val targetRight = rightService.getRight(
                        callerAddr = loginAddr,
                        deployer = targetLicense.rightKeyPair.deployer,
                        index = targetLicense.rightKeyPair.arrayIndex.toBigInteger()
                    )

                    when {
                        (targetLicense.deployer != loginAddr) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "提交者与授权者不匹配")
                            return@get
                        }
                    }

                    val fileHash = targetRight.fileHash
                    val fileName = targetRight.fileName
                    val fileBytes = ipfsService.download(fileHash)

                    call.fileRespond(fileName, fileBytes)
                }

                get("/by-right") {
                    val rightDeployer = call.parameters["deployer"]
                    val rightIndex = call.parameters["index"]

                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    when {
                        (rightDeployer == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的部署者")
                            return@get
                        }

                        (rightIndex == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }

                        (rightIndex.toIntOrNull() == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的索引")
                            return@get
                        }
                    }

                    val targetRight = rightService.getRight(loginAddr!!, rightDeployer!!, rightIndex!!.toLong())

                    when {
                        (targetRight.deployer != loginAddr) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "提交者与授权者不匹配")
                            return@get
                        }
                    }

                    val fileHash = targetRight.fileHash
                    val fileName = targetRight.fileName
                    val fileBytes = ipfsService.download(fileHash)

                    call.fileRespond(fileName, fileBytes)
                }
            }
        }
    }
}