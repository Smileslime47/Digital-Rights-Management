package moe._47saikyo.models

import io.ktor.server.application.*
import io.ktor.server.response.*
import moe._47saikyo.constant.GlobalConstant

/**
 * Http响应体，建议在任何时候都将数据封装在该对象中
 * 1.可以保证前端直接接收到的数据永远为格式固定的JSON对象，便于解析
 * 2.可以保证响应体不会由于状态码非200而被Axios等前端网络框架拦截，无法获取详细的报错信息
 */
data class HttpResponse(val status: HttpStatus = HttpStatus.SUCCESS, val data: Any = GlobalConstant.NULL_PLACEHOLDER)

/**
 * 将响应结果封装在HttpResponse对象中再调用[ApplicationCall.respond]
 *
 * @param status HttpStatus对象，状态码及信息
 * @param data 数据对象
 */
suspend fun ApplicationCall.httpRespond(status: HttpStatus = HttpStatus.SUCCESS, data: Any = GlobalConstant.NULL_PLACEHOLDER)
    = this.respond(HttpResponse(status,data))