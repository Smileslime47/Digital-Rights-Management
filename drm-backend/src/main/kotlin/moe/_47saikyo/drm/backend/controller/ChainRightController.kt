package moe._47saikyo.drm.backend.controller

import io.ktor.server.application.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.configuration.security.authenticateRequired
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.blockchain.service.RightService
import org.koin.ktor.ext.inject

/**
 * Right Contract Controller
 *
 * @author 刘一邦
 */
fun Application.chainRightController() {
    val rightService: RightService by inject()

    routing {
        route("/chain/right") {
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
                get {
                    val addr = call.parameters["addr"]
                    val caller = call.parameters["caller"]


                    when {
                        //addr为空
                        (addr == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                            return@get
                        }

                        //caller为空
                        (caller == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的调用者")
                            return@get
                        }
                    }

                    val rights = rightService.getPureData(caller!!, addr!!)

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }

                //获取指定钱包所部署的版权合约列表
                get("/deployer") {
                    val addr = call.parameters["addr"]

                    when {
                        //addr为空
                        (addr == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                            return@get
                        }
                    }

                    val rights = rightService.getRights(addr!!)

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }

                //通过标题模糊搜索版权合约
                get("/search") {
                    val title = call.parameters["title"]
                    val caller = call.parameters["caller"]

                    when {
                        //title为空
                        (title == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的标题")
                            return@get
                        }

                        //caller为空
                        (caller == null) -> {
                            call.httpRespond(HttpStatus.BAD_REQUEST with "无效的调用者")
                            return@get
                        }
                    }

                    val rights = rightService.searchByTitle(caller!!, title!!).map { rightService.getPureData(caller, it) }

                    call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
                }
            }
        }
    }
}