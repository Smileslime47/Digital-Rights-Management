package moe._47saikyo.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.Group
import domain.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.constant.getProperties
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.UserService
import moe._47saikyo.constant.getProperty
import moe._47saikyo.plugins.security.authenticateAfterLogin
import moe._47saikyo.service.GroupService
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import java.util.*

/**
 * User HTTP Controller
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
fun Application.userController() {
    val userService: UserService by inject(UserService::class.java)
    val groupService: GroupService by inject(GroupService::class.java)
    val properties = getProperties()

    routing {
        route("/user") {
            authenticateAfterLogin(Constant.Authentication.NEED_LOGIN) {
                get {
                    //检查参数格式
                    val targetIdStr = call.request.queryParameters["id"]
                    if (targetIdStr == null || !targetIdStr.matches(Regex("[0-9]*"))) {
                        call.respond(HttpResponse(HttpStatus.INVALID_ARGUMENT))
                        return@get
                    }

                    //检查用户是否存在
                    val targetId = targetIdStr.toLong()
                    val targetUser = userService.getUser(targetId)
                    if (targetUser == null) {
                        call.respond(HttpResponse(HttpStatus.NOT_FOUND))
                        return@get
                    }

                    //检查该用户是否有展示权限
                    val loginId = call.principal<JWTPrincipal>()!!.payload.getClaim("userid")
                    if (!loginId.equals(targetId) && !groupService.authenticate(
                            targetUser.permissionId,
                            Group::permissionShowProfile
                        )
                    ) {
                        call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "该用户无展示信息权限")))
                        return@get
                    }

                    call.respond(HttpResponse(HttpStatus.SUCCESS, targetUser))
                }
            }

            post("/login") {
                data class LoginForm(
                    val username: String,
                    val password: String,
                    val rememberMe: Boolean,
                )

                val loginForm = call.receive<LoginForm>()

                //检查用户合法性
                val loginUser = userService.getUser(loginForm.username)
                if (loginUser == null || loginUser.password != loginForm.password) {
                    call.respond(HttpResponse(HttpStatus.FORBIDDEN))
                    return@post
                }

                //检查用户组登陆权限
                if (!groupService.authenticate(loginUser.permissionId, Group::permissionLogin)) {
                    call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "该用户组已被封禁")))
                    return@post
                }

                //签发jwt
                val jwtSubject = properties.jwtSubject
                val jwtIssuer = properties.jwtIssuer
                val jwtAudience = properties.jwtAudience
                val jwtSecret = properties.jwtSecret
                val token = JWT.create()
                    //主题:Digital Right Manager Access License
                    .withSubject(jwtSubject)
                    //签发者：Digital Right Manager
                    .withIssuer(jwtIssuer)
                    //签发受众：Browser Application
                    .withAudience(jwtAudience)
                    //被签发人身份
                    .withClaim(Constant.Authentication.USER_ID_CLAIM, loginUser.id)
                    .withClaim(Constant.Authentication.GROUP_ID_CLAIM, loginUser.permissionId)
                    //签发日期
                    .withIssuedAt(Date(System.currentTimeMillis()))
                    //过期日期
                    .withExpiresAt(Date(System.currentTimeMillis() + 3600000))//一小时
                    .sign(Algorithm.HMAC256(jwtSecret))

                call.respond(HttpResponse(HttpStatus.SUCCESS, token))
            }
        }
    }
}
