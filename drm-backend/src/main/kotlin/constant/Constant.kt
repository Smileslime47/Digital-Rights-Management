package moe._47saikyo.constant

/**
 * 常量类
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
object Constant {
    object Authentication {
        const val TOKEN_STORAGE = "token"
        const val USER_ID_CLAIM = "user_id"
        const val GROUP_ID_CLAIM = "group_id"
        const val EXPIRE_TIME_CLAIM = "expire_time"

        const val DEFAULT_GROUP_ID = 0L
        const val DEFAULT_EXPIRE_TIME = 3600000L
        const val REMEMBER_ME_EXPIRE_TIME = DEFAULT_EXPIRE_TIME * 24

        const val NEED_LOGIN = "need_login"
    }

    object RespondField{
        const val USER = "user"
        const val GROUP = "group"
        const val SELF_PROFILE = "self"
        const val SUCCESS = "success"
    }

    object PropertyUrl {
        const val DATASOURCE_DRIVER = "exposed.datasource.driverClassName"
        const val DATASOURCE_URL = "exposed.datasource.jdbcUrl"
        const val DATASOURCE_USERNAME = "exposed.datasource.username"
        const val DATASOURCE_PASSWORD = "exposed.datasource.password"

        const val JWT_SECRET = "JWT.secret"
        const val JWT_ISSUER = "JWT.issuer"
        const val JWT_AUDIENCE = "JWT.audience"
        const val JWT_SUBJECT = "JWT.subject"
    }
}