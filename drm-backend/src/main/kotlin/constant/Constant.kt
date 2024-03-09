package moe._47saikyo.constant

/**
 * 常量类
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
object Constant {
    object Global {
        const val NULL_PLACEHOLDER = "N/A"
    }

    object Authentication {
        const val USER_ID_CLAIM = "user_id"
        const val GROUP_ID_CLAIM = "group_id"
        const val NEED_LOGIN = "need_login"
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