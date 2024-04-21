package moe._47saikyo.controller

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.configuration.security.authenticateRequired
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.fileRespond
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.*
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
            authenticateRequired(Constant.Authentication.NEED_LOGIN){
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
                    val addr = call.request.queryParameters["addr"]
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    val targetLicense = licenseService.getPureData(loginAddr!!, addr!!)
                    val targetRight = rightService.getPureData(loginAddr, targetLicense.rightAddr)

                    when {
                        (targetLicense.deployer != loginAddr) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "提交者与授权者不匹配")
                            return@get
                        }
                    }

                    val fileHash = targetRight.fileHash
                    val fileName = targetRight.fileName
                    val fileBytes = ipfsService.download(fileHash)

                    call.fileRespond(fileName,fileBytes)
                }

                get("/by-right") {
                    val addr = call.request.queryParameters["addr"]
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()

                    val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                    logger.info("loginId: $loginId")
                    logger.info("loginAddr: $loginAddr")
                    logger.info("addr: $addr")

                    val targetRight = rightService.getPureData(loginAddr!!, addr!!)

                    when {
                        (targetRight.deployer != loginAddr) -> {
                            call.httpRespond(HttpStatus.FORBIDDEN with "提交者与授权者不匹配")
                            return@get
                        }
                    }

                    val fileHash = targetRight.fileHash
                    val fileName = targetRight.fileName
                    val fileBytes = ipfsService.download(fileHash)

                    call.fileRespond(fileName,fileBytes)
                }
            }
        }
    }
}