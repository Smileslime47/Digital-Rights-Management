package moe._47saikyo.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.service.AccountService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent

fun Application.chainRightController() {
    val accountService: AccountService by KoinJavaComponent.inject(AccountService::class.java)
    val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)

    routing{
        route("/chain/right") {
            authenticate(Constant.Authentication.NEED_BLOCK_ACCOUNT) {
//                post("/add") {
//                    val rightName = call.parameters["rightName"]
//                    val issueTime = call.parameters["issueTime"]
//                    val expireTime = call.parameters["expireTime"]
//
//                    if (rightName == null || issueTime == null || expireTime == null) {
//                        call.respondText("Missing parameters", status = HttpStatusCode.BadRequest)
//                        return@post
//                    }
//
//                    val right = rightService.addRight(
//                        accountService.getTransactionManager(),
//                        rightName,
//                        issueTime.toBigInteger(),
//                        expireTime.toBigInteger()
//                    )
//
//                    call.respondText(right.address)
//                }
//
//                get("/get") {
//                    val rightAddr = call.parameters["rightAddr"]
//
//                    if (rightAddr == null) {
//                        call.respondText("Missing parameters", status = HttpStatusCode.BadRequest)
//                        return@get
//                    }
//
//                    val right = rightService.getRight(
//                        accountService.getTransactionManager(),
//                        rightAddr
//                    )
//
//                    call.respondText(right.name)
//                }
            }
        }
    }
}