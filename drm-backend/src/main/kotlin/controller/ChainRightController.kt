package moe._47saikyo.controller

import io.ktor.server.application.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.*
import org.koin.ktor.ext.inject

fun Application.chainRightController() {
    val rightService: RightService by inject()

    routing {
        route("/chain/right") {
            get {
                val addr = call.parameters["addr"]

                when {
                    //addr为空
                    (addr == null) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                        return@get
                    }
                }

                val rights = rightService.getRights(addr!!)

                call.httpRespond(
                    data = mapOf(
                        Constant.RespondField.RIGHT to rights
                    )
                )
            }
        }
    }
}