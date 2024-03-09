package moe._47saikyo.constant

import kotlinx.serialization.Serializable

/**
 * Http自定义状态码，建议使用伴生对象内的预设内容
 *
 * @param code 状态码
 * @param msg 状态信息
 * @author 刘一邦
 */
class HttpStatus(val code: Int = 200, val msg: String = "Success") {
    class Code {
        companion object {
            //成功
            const val SUCCESS = 200

            //参数错误
            const val INVALID_ARGUMENT = 400

            //未登录
            const val UNAUTHORIZED = 401

            //权限不足
            const val FORBIDDEN = 403

            //资源不存在
            const val NOT_FOUND = 404
        }
    }

    companion object {
        //成功
        val SUCCESS = HttpStatus(Code.SUCCESS, "Success")

        //参数错误
        val INVALID_ARGUMENT = HttpStatus(Code.INVALID_ARGUMENT, "Invalid Argument")

        //未登录
        val UNAUTHORIZED = HttpStatus(Code.UNAUTHORIZED, "Unauthorized")

        //权限不足
        val FORBIDDEN = HttpStatus(Code.FORBIDDEN, "Forbidden")

        //资源不存在
        val NOT_FOUND = HttpStatus(Code.NOT_FOUND, "Not Found")
    }
}