package moe._47saikyo.configuration.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.Group
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.constant.getProperty
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.GroupService
import org.koin.java.KoinJavaComponent

/**
 * Auth鉴权模块
 *
 * @author 刘一邦
 * @since 2024/01/21
 *
 */
fun Application.configureSecurity() {
    val groupService: GroupService by KoinJavaComponent.inject(GroupService::class.java)

    val jwtSubject = getProperty(Constant.PropertyUrl.JWT_SUBJECT)
    val jwtIssuer = getProperty(Constant.PropertyUrl.JWT_ISSUER)
    val jwtAudience = getProperty(Constant.PropertyUrl.JWT_AUDIENCE)
    val jwtSecret = getProperty(Constant.PropertyUrl.JWT_SECRET)

    val jwtTemplate = JWT
        .require(Algorithm.HMAC256(jwtSecret))
        //主题:Digital Right Manager Access License
        .withSubject(jwtSubject)
        //签发者：Digital Right Manager
        .withIssuer(jwtIssuer)
        //签发受众：Browser Application
        .withAudience(jwtAudience)
        .build()

    val authenticateGroupPermission: ((group: Group) -> Boolean) -> (JWTAuthenticationProvider.Config) -> Unit =
        { condition ->
            {
                it.verifier(jwtTemplate)
                it.validate { jwtCredential ->
                    val groupStr = jwtCredential.payload.claims[Constant.Authentication.GROUP_ID_CLAIM]
                    val groupId = groupStr?.asLong() ?: return@validate null
                    val group = groupService.getGroup(groupId)
                    if (group != null && condition(group)) {
                        JWTPrincipal(jwtCredential.payload)
                    } else {
                        null
                    }
                }
                it.challenge { _, _ ->
                    call.respond(HttpResponse(HttpStatus.FORBIDDEN))
                }
            }
        }

    install(Authentication) {
        //仅检查是否包含token,用于要求登陆
        jwt(Constant.Authentication.NEED_LOGIN) {
            verifier(jwtTemplate)
            validate { JWTPrincipal(it.payload) }
            challenge { _, _ ->
                call.respond(HttpResponse(HttpStatus.UNAUTHORIZED))
            }
        }

        jwt(Constant.Authentication.PERMISSION_CREATE_CHAIN_ACCOUNT) {
            authenticateGroupPermission(Group::permissionCreateChainAccount)
        }
    }
}

/**
 * 鉴权前先检查登陆有效性
 *
 * @author 刘一邦
 */
fun Route.authenticateAfterLogin(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    build: Route.() -> Unit
): Route {
    return authenticate(Constant.Authentication.NEED_LOGIN) {
        authenticate(
            configurations = configurations,
            strategy = if (optional) AuthenticationStrategy.Optional else AuthenticationStrategy.FirstSuccessful,
            build = build
        )
    }
}