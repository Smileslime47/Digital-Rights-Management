package moe._47saikyo.controller

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.GroupService
import org.koin.java.KoinJavaComponent

fun Application.groupController() {
    val groupService: GroupService by KoinJavaComponent.inject(GroupService::class.java)

    routing {
        route("/group"){
            authenticate(Constant.Authentication.NEED_LOGIN){
                get {
                    //检查参数格式
                    val targetIdStr = call.request.queryParameters["id"]
                    if (targetIdStr == null || !targetIdStr.matches(Regex("[0-9]*"))) {
                        call.respond(HttpResponse(HttpStatus.BAD_REQUEST))
                        return@get
                    }

                    //检查用户组是否存在
                    val targetId = targetIdStr.toLong()
                    val targetGroup = groupService.getGroup(targetId)
                    if (targetGroup == null) {
                        call.respond(HttpResponse(HttpStatus.NOT_FOUND))
                        return@get
                    }

                    call.respond(HttpResponse(HttpStatus.SUCCESS, mapOf(
                        Constant.RespondField.GROUP to targetGroup
                    )))
                }
            }
        }
    }
}