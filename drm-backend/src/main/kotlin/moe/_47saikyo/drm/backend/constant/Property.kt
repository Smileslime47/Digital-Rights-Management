package moe._47saikyo.drm.backend.constant

import io.ktor.server.application.*
import java.util.*

/**
 * 获取Application的Property字段
 *
 * @author 刘一邦
 * @since 2024/01/21
 */
fun Application.getProperty(key: String): String? {
    val env = environment.config.propertyOrNull(Constant.PropertyUrl.ENV)?.getString()

    val globalVal = environment.config.propertyOrNull("${Constant.PropertyUrl.GLOBAL}.${key}")?.getString()

    if (globalVal != null) return globalVal

    return environment.config.propertyOrNull("${env}.${key}")?.getString()
}

/**
 * 环境变量类，防止在其他处理函数中重复调用getProperty函数
 *
 * @author 刘一邦
 */
class Properties(
    val jwtSubject: String?,
    val jwtIssuer: String?,
    val jwtAudience: String?,
    val jwtSecret: String?,
)

/**
 * 通过该函数初始化并获取上述环境变量类
 *
 * @return 环境变量类
 * @author 刘一邦
 */
fun Application.getProperties(): Properties = Properties(
    jwtSubject = getProperty(Constant.PropertyUrl.JWT_SUBJECT),
    jwtIssuer = getProperty(Constant.PropertyUrl.JWT_ISSUER),
    jwtAudience = getProperty(Constant.PropertyUrl.JWT_AUDIENCE),
    jwtSecret = getProperty(Constant.PropertyUrl.JWT_SECRET),
)