package moe._47saikyo.drm.backend.controller

import io.ktor.server.application.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.configuration.security.authenticateRequired
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.GroupService
import org.koin.ktor.ext.inject

/**
 * User Group Controller
 *
 * @author 刘一邦
 */
fun Application.groupController() {
    val groupService: GroupService by inject()

    routing {
        route("/group") {
            get {
                //检查参数格式
                val targetIdStr = call.request.queryParameters["id"]
                if (targetIdStr == null || !targetIdStr.matches(Regex("[0-9]*"))) {
                    call.httpRespond(HttpStatus.BAD_REQUEST)
                    return@get
                }

                //检查用户组是否存在
                val targetId = targetIdStr.toLong()
                val targetGroup = groupService.getGroup(targetId)
                if (targetGroup == null) {
                    call.httpRespond(HttpStatus.NOT_FOUND)
                    return@get
                }

                call.httpRespond(data = mapOf(Constant.RespondField.GROUP to targetGroup))
            }
        }
    }
}