package moe._47saikyo.controller

import constant.GlobalConstant
import domain.Notice
import enums.NoticeStatus
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.NoticeService
import moe._47saikyo.service.UserService
import org.koin.java.KoinJavaComponent.inject

fun Application.noticeController() {
    val noticeService: NoticeService by inject(NoticeService::class.java)
    val userService: UserService by inject(UserService::class.java)

    routing {
        route("/notice") {
            authenticate(Constant.Authentication.NEED_LOGIN) {
                //获取通知数量
                get("/count") {
                    val filterStr = call.request.queryParameters["filter"]
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginUser = loginId?.let { id -> userService.getUser(id) }

                    when {
                        //检查过滤器合法性
                        (filterStr == null || filterStr !in GlobalConstant.NOTICE_FILTERS) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的通知过滤器")
                            return@get
                        }
                    }

                    val count = when {
                        (filterStr!!.equals("ALL", ignoreCase = true)) -> noticeService.countNotices(loginUser!!.id)
                        (filterStr.equals("UNREAD", ignoreCase = true)) -> noticeService.countUnreadNotices(loginUser!!.id)
                        (filterStr.equals("READ", ignoreCase = true)) -> noticeService.countReadNotices(loginUser!!.id)
                        (filterStr.equals("ARCHIVED", ignoreCase = true)) -> noticeService.countArchivedNotices(loginUser!!.id)
                        else -> 0//不可能到达的分支
                    }

                    call.httpRespond(data = mapOf(Constant.RespondField.COUNT to count))
                }

                //获取通知列表
                get {
                    val filterStr = call.request.queryParameters["filter"]
                    val pageNumberStr = call.request.queryParameters["page"]
                    val pageSize = Constant.DEFAULT_PAGE_SIZE
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginUser = loginId?.let { id -> userService.getUser(id) }

                    when {
                        //检查过滤器合法性
                        (filterStr == null || filterStr !in GlobalConstant.NOTICE_FILTERS) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的通知过滤器")
                            return@get
                        }

                        //检查页码合法性
                        (pageNumberStr == null || !pageNumberStr.matches(Regex("[0-9]*"))) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的页码")
                            return@get
                        }
                    }

                    val (count, pages:List<Notice>) = when {
                        (filterStr!!.equals("ALL", ignoreCase = true)) ->
                            (noticeService.countNotices(loginUser!!.id)         to noticeService.getNotices(pageSize, pageNumberStr!!.toInt(), loginUser.id))
                        (filterStr.equals("UNREAD", ignoreCase = true)) ->
                            (noticeService.countUnreadNotices(loginUser!!.id)   to noticeService.getUnreadNotices(pageSize, pageNumberStr!!.toInt(), loginUser.id))
                        (filterStr.equals("READ", ignoreCase = true)) ->
                            (noticeService.countReadNotices(loginUser!!.id)     to noticeService.getReadNotices(pageSize, pageNumberStr!!.toInt(), loginUser.id))
                        (filterStr.equals("ARCHIVED", ignoreCase = true)) ->
                            (noticeService.countArchivedNotices(loginUser!!.id) to noticeService.getArchivedNotices(pageSize, pageNumberStr!!.toInt(), loginUser.id))
                        else -> (0 to emptyList())//不可能到达的分支
                    }

                    call.httpRespond(data = mapOf(
                        Constant.RespondField.NOTICE to pages,
                        Constant.RespondField.COUNT to count
                    ))
                }

                post{
                    data class Form(
                        val noticeId: Long,
                        val targetStatus: String
                    )
                    val noticeForm = call.receive<Form>()
                    val loginId =
                        call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            ?.asLong()
                    val loginUser = loginId?.let { id -> userService.getUser(id) }
                    val notice = noticeService.getNotice(noticeForm.noticeId)

                    when {
                        //检查通知用户和登陆用户一致性
                        (notice == null || notice.receiverId != loginUser!!.id) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的通知ID")
                            return@post
                        }

                        //检查通知ID合法性
                        (noticeForm.noticeId <= 0) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的通知ID")
                            return@post
                        }

                        //检查目标状态合法性
                        (noticeForm.targetStatus !in GlobalConstant.NOTICE_FILTERS) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的目标状态")
                            return@post
                        }
                    }

                    //修改notice状态
                    notice!!.status = NoticeStatus.fromString(noticeForm.targetStatus)

                    when (noticeService.updateNotice(notice)) {
                        true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                        false -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                    }
                }
            }
        }
    }
}