package moe._47saikyo.drm.backend.configuration.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.constant.getProperty
import moe._47saikyo.drm.backend.models.HttpResponse
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.GroupService
import moe._47saikyo.drm.backend.service.UserService
import moe._47saikyo.drm.backend.service.WalletService
import moe._47saikyo.drm.core.domain.Group
import org.koin.java.KoinJavaComponent.inject

/**
 * Auth鉴权模块
 *
 * @author 刘一邦
 * @since 2024/01/21
 *
 */
fun Application.configureSecurity() {
    val logger = org.slf4j.LoggerFactory.getLogger("Security")
    val userService: UserService by inject(UserService::class.java)
    val groupService: GroupService by inject(GroupService::class.java)
    val walletService: WalletService by inject(WalletService::class.java)

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

    val authenticateGroupPermission: ((group: Group) -> Boolean) -> JWTAuthenticationProvider.Config.() -> Unit =
        { condition ->
            {
                verifier(jwtTemplate)
                validate {
                    val groupStr = it.payload.claims[Constant.Authentication.GROUP_ID_CLAIM]
                    val groupId = groupStr?.asLong() ?: return@validate null
                    if (groupService.authenticate(groupId, condition)) {
                        JWTPrincipal(it.payload)
                    } else {
                        null
                    }
                }
                challenge { _, _ ->
                    call.respond(HttpResponse(HttpStatus.FORBIDDEN with "需要权限：$condition"))
                }
            }
        }

    install(Authentication) {
        //仅检查是否包含token,用于要求登陆
        jwt(Constant.Authentication.NEED_LOGIN) {
            verifier(jwtTemplate)
            validate {
                logger.info("Validate if user exists.")
                val loginId = it.payload.getClaim(Constant.Authentication.USER_ID_CLAIM).asLong()
                val loginUser = loginId?.let { id -> userService.getUser(id) }
                if (loginId != null && loginUser != null) {
                    logger.info("User exists.")
                    JWTPrincipal(it.payload)
                } else {
                    logger.info("User does not exists.")
                    null
                }
            }
            challenge { _, _ ->
                call.httpRespond(HttpStatus.UNAUTHORIZED with "未登录或登陆状态无效")
            }
        }

        //验证包含链上账户
        jwt(Constant.Authentication.NEED_BLOCK_ACCOUNT) {
            verifier(jwtTemplate)
            validate {
                val userId = it.payload.getClaim(Constant.Authentication.USER_ID_CLAIM).asLong()
                val wallet = walletService.getWallet(userId)
                if (wallet?.walletFile != null && wallet.walletFile.isNotEmpty()) {
                    JWTPrincipal(it.payload)
                } else {
                    throw Exception()
                }
            }
            challenge { _, _ ->
                call.httpRespond(HttpStatus.NEED_ACCOUNT with "需要先开通链上账户")
            }
        }

        jwt(Constant.Authentication.PERMISSION_CREATE_CHAIN_ACCOUNT, authenticateGroupPermission(Group::permissionCreateChainAccount))

        jwt(Constant.Authentication.PERMISSION_CREATE_RIGHT, authenticateGroupPermission(Group::permissionCreateRight))

        jwt(Constant.Authentication.PERMISSION_VERIFY_RIGHT, authenticateGroupPermission(Group::permissionVerifyRight))

        jwt(Constant.Authentication.PERMISSION_CREATE_LICENSE, authenticateGroupPermission(Group::permissionCreateLicense))

        jwt(Constant.Authentication.PERMISSION_VERIFY_LICENSE, authenticateGroupPermission(Group::permissionVerifyLicense))
    }
}

/**
 * 鉴权前先检查登陆有效性
 *
 * @author 刘一邦
 */
fun Route.authenticateAll(
    configurations: List<String>,
    build: Route.() -> Unit
): Route =
    if (configurations.size > 1) {
        authenticateRequired(configurations[0]) {
            authenticateAll(configurations.drop(1), build)
        }
    } else {
        authenticateRequired(configurations[0]) {
            build()
        }
    }

/**
 * 鉴权策略
 */
fun Route.authenticateRequired(
    vararg configurations: String? = arrayOf(null),
    build: Route.() -> Unit
): Route = authenticate(configurations = configurations, strategy = AuthenticationStrategy.Required, build)