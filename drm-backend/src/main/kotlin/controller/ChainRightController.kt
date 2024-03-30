package moe._47saikyo.controller

import domain.Group
import domain.Notice
import domain.PendingRight
import domain.PendingStatus
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.models.HttpStatus
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.models.httpRespond
import moe._47saikyo.service.*
import org.koin.java.KoinJavaComponent.inject
import java.math.BigInteger

fun Application.chainRightController() {
    val accountService: AccountService by inject(AccountService::class.java)
    val walletService: WalletService by inject(WalletService::class.java)
    val rightService: RightService by inject(RightService::class.java)
    val pendingRightService: PendingRightService by inject(PendingRightService::class.java)
    val userService: UserService by inject(UserService::class.java)
    val groupService: GroupService by inject(GroupService::class.java)

    routing {
        route("/chain/right") {
            get {
                val addr = call.parameters["addr"]

                if (addr == null) {
                    call.httpRespond(HttpStatus.BAD_REQUEST with "Missing parameters")
                    return@get
                }

                val right = pendingRightService.getPendingRights(addr)

                call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to right))
            }

            authenticate(Constant.Authentication.NEED_LOGIN) {
                authenticate(Constant.Authentication.NEED_BLOCK_ACCOUNT) {
                    post("/deploy"){
                        //获取表单
                        data class Form(
                            val password:String,
                            val pendingId:Long
                        )
                        val form = call.receive<Form>()

                        //获取信息
                        val loginId = call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)?.asLong()
                        val walletFileContent = walletService.getWallet(loginId!!)?.walletFile
                        val pwd = form.password
                        val pendingId = form.pendingId

                        //获取txManager
                        val txManager = accountService.getTxManager(pwd,walletFileContent!!)

                        //获取pendingRight并生成部署表单
                        val pendingRight = pendingRightService.getPendingRight(pendingId)
                        val deployForm = RightDeployForm(
                            title = pendingRight!!.title,
                            registrationNumber = pendingRight.registrationNumber,
                            issueTime = BigInteger.valueOf(pendingRight.issueTime),
                            expireTime = BigInteger.valueOf(pendingRight.expireTime),
                            description = pendingRight.description,
                        )

                        //部署版权
                        val right = rightService.addRight(txManager,deployForm)

                        call.httpRespond(data = mapOf(Constant.RespondField.RIGHT to right.contractAddress))
                    }

                    authenticate(Constant.Authentication.PERMISSION_CREATE_RIGHT) {
                        post {
                            val targetRight = call.receive<PendingRight>()
                            val loginId =
                                call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                                    ?.asLong()
                            val loginUser = loginId?.let { id -> userService.getUser(id) }
                            val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                            when {
                                //版权申请人和登陆用户一致性
                                (loginAddr != targetRight.owner) -> {
                                    call.httpRespond(HttpStatus.FORBIDDEN with "无权代理他人申请版权")
                                    return@post
                                }

                                //检查版权状态
                                (targetRight.status != PendingStatus.PENDING && groupService.authenticate(loginUser!!.permissionId, Group::permissionVerifyRight)) -> {
                                    call.httpRespond(HttpStatus.FORBIDDEN with "非管理员用户仅能提交待审核版权申请")
                                    return@post
                                }
                            }

                            //插入待审核版权
                            val pendingRight = pendingRightService.insertPendingRight(targetRight)

                            //发送通知
                            when(pendingRight!=null){
                                true->call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                                false->call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                            }
                        }
                    }

                    authenticate(Constant.Authentication.PERMISSION_VERIFY_RIGHT){
                        post("/verify"){
                            val targetRight = call.receive<PendingRight>()

                            //发送通知
                            when(pendingRightService.confirmPendingRight(targetRight.id)){
                                true->call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                                false->call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                            }
                        }
                    }
                }
            }
        }
    }
}