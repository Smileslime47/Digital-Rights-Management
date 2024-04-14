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

                var fileName = ""
                var fileHash = ""
                var fileBytes: ByteArray

                multipartData.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        fileName = part.originalFileName?:"UnnamedFile"
                        fileBytes = part.streamProvider().readBytes()
                        fileHash = ipfsService.upload(fileBytes)
                    }
                    part.dispose()
                }


                call.httpRespond(data = mapOf(
                    Constant.RespondField.NAME to fileName,
                    Constant.RespondField.HASH to fileHash
                ))
            }
            get {

            }
        }
    }
}