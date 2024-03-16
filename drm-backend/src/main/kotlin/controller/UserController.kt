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
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus
import moe._47saikyo.constant.getProperties
import moe._47saikyo.models.HttpResponse
import moe._47saikyo.service.UserService
import moe._47saikyo.plugins.security.PasswordEncoder
import moe._47saikyo.service.GroupService
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
    val passwordEncoder: PasswordEncoder by inject(PasswordEncoder::class.java)
    val properties = getProperties()

    routing {
        route("/user") {
            authenticate(Constant.Authentication.NEED_LOGIN) {
                get {
                    //检查参数格式
                    val targetIdStr = call.request.queryParameters["id"]
                    if (targetIdStr == null || !targetIdStr.matches(Regex("[0-9]*"))) {
                        call.respond(HttpResponse(HttpStatus.BAD_REQUEST))
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
                    val loginId =
                        call.principal<JWTPrincipal>()!!.payload.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            .asLong()
                    when {
                        loginId.equals(targetId) -> {
                            call.respond(
                                HttpResponse(
                                    data = mapOf(
                                        Constant.RespondField.USER to User(
                                            id = targetUser.id,
                                            permissionId = targetUser.permissionId,
                                            username = targetUser.username,
                                            nickname = targetUser.nickname,
                                            password = targetUser.password,
                                            email = targetUser.email,
                                            phoneNumber = targetUser.phoneNumber
                                        ),
                                        Constant.RespondField.SELF_PROFILE to true
                                    )
                                )
                            )
                            return@get
                        }

                        groupService.authenticate(targetUser.permissionId, Group::permissionShowProfile) -> {
                            call.respond(
                                HttpResponse(
                                    data = mapOf(
                                        Constant.RespondField.USER to User(
                                            id = targetUser.id,
                                            permissionId = targetUser.permissionId,
                                            username = targetUser.username,
                                            nickname = targetUser.nickname,
                                            email = targetUser.email,
                                            phoneNumber = targetUser.phoneNumber
                                        ),
                                        Constant.RespondField.SELF_PROFILE to false
                                    )
                                )
                            )
                            return@get
                        }

                        else -> {
                            call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "该用户无展示信息权限")))
                            return@get
                        }
                    }
                }

                post {
                    val targetUser = call.receive<User>()

                    //检查用户是否存在
                    if (userService.getUser(targetUser.id) == null) {
                        call.respond(HttpResponse(HttpStatus.NOT_FOUND))
                        return@post
                    }

                    //检查该用户是否要修改自身信息
                    val loginId =
                        call.principal<JWTPrincipal>()!!.payload.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            .asLong()
                    if (loginId != targetUser.id) {
                        call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "禁止修改他人信息")))
                        return@post
                    }

                    //返回修改结果
                    when (userService.updateUser(targetUser)) {
                        true -> call.respond(
                            HttpResponse(
                                data = mapOf(Constant.RespondField.SUCCESS to true)
                            )
                        )

                        false -> call.respond(
                            HttpResponse(
                                data = mapOf(Constant.RespondField.SUCCESS to false)
                            )
                        )
                    }
                }

                post("/change-password") {
                    data class ChangePasswordForm(
                        val oldPassword: String,
                        val newPassword: String,
                        val confirmPassword: String
                    )

                    val changePasswordForm = call.receive<ChangePasswordForm>()

                    //新密码应当与确认密码相同
                    if (changePasswordForm.newPassword != changePasswordForm.confirmPassword) {
                        call.respond(HttpResponse(HttpStatus(HttpStatus.Code.BAD_REQUEST, "两次密码不匹配")))
                        return@post
                    }

                    //检查用户合法性
                    val loginId =
                        call.principal<JWTPrincipal>()!!.payload.getClaim(Constant.Authentication.USER_ID_CLAIM)
                            .asLong()
                    val loginUser = userService.getUser(loginId)
                    if (loginUser == null || !passwordEncoder.verifyPassword(
                            changePasswordForm.oldPassword,
                            loginUser.password
                        )
                    ) {
                        call.respond(HttpResponse(HttpStatus(HttpStatus.Code.INVALID_TOKEN, "密码不符")))
                        return@post
                    }

                    loginUser.password = passwordEncoder.hashPassword(changePasswordForm.newPassword)

                    //返回修改结果
                    when (userService.updateUser(loginUser)) {
                        true -> call.respond(
                            HttpResponse(
                                data = mapOf(Constant.RespondField.SUCCESS to true)
                            )
                        )

                        false -> call.respond(
                            HttpResponse(
                                data = mapOf(Constant.RespondField.SUCCESS to false)
                            )
                        )
                    }
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
                if (loginUser == null || !passwordEncoder.verifyPassword(loginForm.password,loginUser.password)) {
                    call.respond(HttpResponse(HttpStatus(HttpStatus.Code.INVALID_TOKEN, "用户名或密码不符")))
                    return@post
                }

                //检查用户组登陆权限
                if (!groupService.authenticate(loginUser.permissionId, Group::permissionLogin)) {
                    call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "该用户组已被封禁")))
                    return@post
                }

                //签发jwt
                val jwtIssueTime = System.currentTimeMillis()
                val jwtExpireTime =
                    jwtIssueTime + if (loginForm.rememberMe) Constant.Authentication.REMEMBER_ME_EXPIRE_TIME else Constant.Authentication.DEFAULT_EXPIRE_TIME
                val token = JWT.create()
                    //主题:Digital Right Manager Access License
                    .withSubject(properties.jwtSubject)
                    //签发者：Digital Right Manager
                    .withIssuer(properties.jwtIssuer)
                    //签发受众：Browser Application
                    .withAudience(properties.jwtAudience)
                    //被签发人身份
                    .withClaim(Constant.Authentication.USER_ID_CLAIM, loginUser.id)
                    .withClaim(Constant.Authentication.GROUP_ID_CLAIM, loginUser.permissionId)
                    //签发日期
                    .withIssuedAt(Date(jwtIssueTime))
                    //过期日期
                    .withExpiresAt(Date(jwtExpireTime))//一小时
                    .sign(Algorithm.HMAC256(properties.jwtSecret))

                call.respond(
                    HttpResponse(
                        data = mapOf(
                            Constant.Authentication.TOKEN_STORAGE to token,
                            Constant.Authentication.USER_ID_CLAIM to loginUser.id,
                            Constant.Authentication.GROUP_ID_CLAIM to loginUser.permissionId,
                            Constant.Authentication.EXPIRE_TIME_CLAIM to jwtExpireTime
                        )
                    )
                )
            }

            post("/register") {
                data class RegisterForm(
                    val username: String,
                    val password: String,
                    val confirmPassword: String,
                    val email: String,
                    val phoneNumber: String,
                )

                val registerForm = call.receive<RegisterForm>()

                //检测用户是否已存在
                if (userService.getUser(registerForm.username) != null) {
                    call.respond(HttpResponse(HttpStatus(HttpStatus.Code.FORBIDDEN, "用户已存在")))
                    return@post
                }

                //插入数据库
                val registerUser = userService.insertUser(
                    User(
                        permissionId = Constant.Authentication.DEFAULT_GROUP_ID,
                        username = registerForm.username,
                        nickname = registerForm.username,
                        password = passwordEncoder.hashPassword(registerForm.password),
                        email = registerForm.email,
                        phoneNumber = registerForm.phoneNumber
                    )
                )

                //检查插入结果
                if (registerUser == null) {
                    call.respond(HttpResponse(HttpStatus.SERVER_ERROR))
                    return@post
                }

                //签发jwt
                val jwtIssueTime = System.currentTimeMillis()
                val jwtExpireTime = jwtIssueTime + Constant.Authentication.DEFAULT_EXPIRE_TIME
                val token = JWT.create()
                    //主题:Digital Right Manager Access License
                    .withSubject(properties.jwtSubject)
                    //签发者：Digital Right Manager
                    .withIssuer(properties.jwtIssuer)
                    //签发受众：Browser Application
                    .withAudience(properties.jwtAudience)
                    //被签发人身份
                    .withClaim(Constant.Authentication.USER_ID_CLAIM, registerUser.id)
                    .withClaim(Constant.Authentication.GROUP_ID_CLAIM, registerUser.permissionId)
                    //签发日期
                    .withIssuedAt(Date(jwtIssueTime))
                    //过期日期
                    .withExpiresAt(Date(jwtExpireTime))//一小时
                    .sign(Algorithm.HMAC256(properties.jwtSecret))

                call.respond(
                    HttpResponse(
                        data = mapOf(
                            Constant.Authentication.TOKEN_STORAGE to token,
                            Constant.Authentication.USER_ID_CLAIM to registerUser.id,
                            Constant.Authentication.GROUP_ID_CLAIM to registerUser.permissionId
                        )
                    )
                )
            }
        }
    }
}