package moe._47saikyo.drm.backend.controller

import io.ktor.server.application.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.ReceiptService
import moe._47saikyo.drm.blockchain.models.KeyPairData
import org.koin.ktor.ext.inject

fun Application.receiptController() {
    val receiptService: ReceiptService by inject()

    routing {
        route("/receipt") {
            get {
                val rightDeployer = call.parameters["deployer"]
                val rightIndex = call.parameters["index"]

                when {
                    rightDeployer == null -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "Invalid deployer")
                        return@get
                    }

                    rightIndex == null -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "Invalid index")
                        return@get
                    }

                    rightIndex.toIntOrNull() == null -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "Invalid index")
                        return@get
                    }
                }

                val receipt = receiptService.getReceiptByKeyPair(KeyPairData(rightDeployer!!, rightIndex!!.toLong()))

                call.httpRespond(
                    data = mapOf(
                        Constant.RespondField.RECEIPT to receipt
                    )
                )
            }
        }
    }
}