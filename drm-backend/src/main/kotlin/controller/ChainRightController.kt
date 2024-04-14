package moe._47saikyo.controller

import io.ktor.server.application.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.*
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
            get {
                val addr = call.parameters["addr"]

                when {
                    //addr为空
                    (addr == null) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                        return@get
                    }
                }

                val rights = rightService.getPureData(addr!!)

                call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
            }

            //获取指定钱包所部署的版权合约列表
            get("/owner") {
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

                when {
                    //title为空
                    (title == null) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的标题")
                        return@get
                    }
                }

                val rights = rightService.searchByTitle(title!!).map { rightService.getPureData(it) }

                call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to rights))
            }
        }
    }
}