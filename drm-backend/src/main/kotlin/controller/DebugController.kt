package moe._47saikyo.controller

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.constant.getProperties
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.UserService
import org.koin.java.KoinJavaComponent

fun Application.debugController() {
    val userService: UserService by KoinJavaComponent.inject(UserService::class.java)
    val properties = getProperties()

    routing {
        route("/debug") {
            get("/user") {
                val debugUser = userService.getDebugUser()
                call.respond(HttpResponse(HttpStatus.SUCCESS, debugUser))
            }

            get("/error_status") {
                call.respond(HttpResponse(HttpStatus.BAD_REQUEST))
            }
        }
    }
}
