package moe._47saikyo.models

import moe._47saikyo.constant.GlobalConstant

/**
 * Http自定义状态码，建议使用伴生对象内的预设内容
 *
 * @param code 状态码
 * @author 刘一邦
 */
data class HttpStatus(val code: Int = 200) {
    //状态信息，默认为[GlobalConstant.NULL_PLACEHOLDER]
    var msg: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    /**
     * 通过with中缀表达式写入状态信息
     * e.g. HttpStatus.BAD_REQUEST with "参数错误"
     *
     * @param msg 状态信息
     * @return 写入状态信息的HttpStatus
     */
    infix fun with(msg: String): HttpStatus {
        this.msg = msg
        return this
    }

    /**
     * 带有状态信息的构造函数，日常使用推荐上面的with表达式
     *
     * @param code 状态码，详见[HttpStatus.Code]
     * @param msg 状态信息
     */
    constructor(code: Int = 200, msg: String) : this(code) {
        this with msg
    }

    class Code {
        companion object {
            //成功
            const val SUCCESS = 200

            //参数错误
            const val BAD_REQUEST = 400

            //未登录
            const val UNAUTHORIZED = 401

            //登陆令牌无效
            const val INVALID_TOKEN = 402

            //权限不足
            const val FORBIDDEN = 403

            //资源不存在
            const val NOT_FOUND = 404

            //需要链上账户
            const val NEED_ACCOUNT = 405

            //服务器错误
            const val SERVER_ERROR = 500
        }
    }

    companion object {
        //成功
        val SUCCESS = HttpStatus(Code.SUCCESS, "Success")

        //参数错误
        val BAD_REQUEST = HttpStatus(Code.BAD_REQUEST, "Bad Request")

        //登陆令牌无效
        val INVALID_TOKEN = HttpStatus(Code.INVALID_TOKEN, "Invalid Token")

        //未登录
        val UNAUTHORIZED = HttpStatus(Code.UNAUTHORIZED, "Unauthorized")

        //权限不足
        val FORBIDDEN = HttpStatus(Code.FORBIDDEN, "Forbidden")

        //资源不存在
        val NOT_FOUND = HttpStatus(Code.NOT_FOUND, "Not Found")

        //需要链上账户
        val NEED_ACCOUNT = HttpStatus(Code.NEED_ACCOUNT, "Need Chain Account")

        //服务器错误
        val SERVER_ERROR = HttpStatus(Code.SERVER_ERROR, "Server Error")
    }
}