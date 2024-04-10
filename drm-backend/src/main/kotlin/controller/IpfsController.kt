package moe._47saikyo.controller

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.IpfsService
import org.koin.ktor.ext.inject

fun Application.ipfsController() {
    val ipfsService: IpfsService by inject()

    routing {
        route("/ipfs") {
            post {
                val multipartData = call.receiveMultipart()

                var fileDescription = ""
                var fileName = ""
                var fileHash = ""
                var fileBytes: ByteArray

                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            fileDescription = part.value
                        }

                        is PartData.FileItem -> {
                            fileName = part.originalFileName as String
                            fileBytes = part.streamProvider().readBytes()
//                            File("uploads/$fileName").writeBytes(fileBytes)
                            fileHash = ipfsService.upload(fileBytes)
                        }

                        else -> {}
                    }
                    part.dispose()
                }

                call.httpRespond(data = mapOf(
                    Constant.RespondField.HASH to fileHash
                ))
            }
            get {

            }
        }
    }
}