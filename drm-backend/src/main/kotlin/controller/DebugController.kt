package moe._47saikyo.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.constant.getProperties
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.UserService
import org.koin.java.KoinJavaComponent
import java.util.*

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
                call.respond(HttpResponse(HttpStatus.INVALID_ARGUMENT))
            }
        }
    }
}
