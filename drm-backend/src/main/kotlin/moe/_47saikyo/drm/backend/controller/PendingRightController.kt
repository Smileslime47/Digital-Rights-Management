package moe._47saikyo.drm.backend.controller

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.drm.backend.configuration.security.authenticateRequired
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.models.HttpStatus
import moe._47saikyo.drm.backend.models.httpRespond
import moe._47saikyo.drm.backend.service.*
import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.blockchain.service.AccountService
import moe._47saikyo.drm.blockchain.service.RightService
import moe._47saikyo.drm.core.domain.Group
import moe._47saikyo.drm.core.domain.Notice
import moe._47saikyo.drm.core.domain.PendingRight
import moe._47saikyo.drm.core.enums.PendingStatus
import org.koin.ktor.ext.inject

/**
 * Pending Right Controller
 *
 * @author 刘一邦
 */
fun Application.pendingRightController() {
    val pendingRightService: PendingRightService by inject()
    val accountService: AccountService by inject()
    val noticeService: NoticeService by inject()
    val walletService: WalletService by inject()
    val rightService: RightService by inject()
    val groupService: GroupService by inject()
    val userService: UserService by inject()

    routing {
        route("/pending-right") {
            //查询指定账户的待审核版权
            get {
                val addr = call.parameters["addr"]
                val pageNumberStr = call.request.queryParameters["page"]
                val pageSize = Constant.DEFAULT_PAGE_SIZE

                when {
                    //addr为空
                    (addr == null) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                        return@get
                    }

                    //检查页码合法性
                    (pageNumberStr == null || !pageNumberStr.matches(Regex("[0-9]*"))) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的页码")
                        return@get
                    }
                }

                val (count, pages) = pendingRightService.countPendingRights(addr!!) to pendingRightService.getPendingRights(pageSize, pageNumberStr!!.toInt(), addr)

                call.httpRespond(
                    data = mapOf(
                        Constant.RespondField.COUNT to count,
                        Constant.RespondField.RIGHT to pages
                    )
                )
            }

            //要求登陆
            authenticateRequired(Constant.Authentication.NEED_LOGIN) {
                //要求登陆、拥有区块链账号
                authenticateRequired(Constant.Authentication.NEED_BLOCK_ACCOUNT) {
                    //部署版权
                    post("/deploy") {
                        //获取表单
                        data class Form(
                            val password: String,
                            val pendingId: Long
                        )

                        val form = call.receive<Form>()

                        //获取信息
                        val loginId = call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)?.asLong()
                        val pwd = form.password
                        val pendingId = form.pendingId

                        //获取Base64钱包文件
                        val dbWallet = walletService.getWallet(loginId!!)
                        //解密钱包文件
                        val walletFileJson = walletService.decryptWallet(dbWallet!!, pwd)

                        //获取txManager
                        val txManager = accountService.getTxManager(pwd, walletFileJson)

                        //部署合约
                        val oldBalance = accountService.getBalance(dbWallet.address)
                        val right = pendingRightService.deployPendingRight(pendingId, txManager)
                        val newBalance = accountService.getBalance(dbWallet.address)

                        noticeService.insertNotice(
                            Notice(
                                title = "版权部署通知",
                                content = "您的版权申请:${right!!.title}已成功部署。",
                                receiverId = loginId,
                                targetRoute = "/chain/right/${right.deployer}/${right.index}"
                            )
                        )

                        call.httpRespond(
                            data = mapOf(
                                Constant.RespondField.COST to newBalance - oldBalance,
                            )
                        )
                    }

                    //要求登陆、拥有区块链账号、拥有创建版权权限
                    authenticateRequired(Constant.Authentication.PERMISSION_CREATE_RIGHT) {
                        //创建新的版权申请
                        post {
                            val targetRight = call.receive<PendingRight>()
                            val loginId =
                                call.principal<JWTPrincipal>()?.payload?.getClaim(Constant.Authentication.USER_ID_CLAIM)
                                    ?.asLong()
                            val loginUser = loginId?.let { id -> userService.getUser(id) }
                            val loginAddr = loginId?.let { id -> walletService.getWallet(id)?.address }

                            when {
                                //版权申请人和登陆用户一致性
                                (loginAddr != targetRight.deployer) -> {
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
                            when (pendingRight != null) {
                                true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                                false -> call.httpRespond(HttpStatus.SERVER_ERROR with "添加版权申请错误，请检查登记号和资源文件是否已存在于系统中")
                            }
                        }
                    }

                    //要求登陆、拥有区块链账号、拥有审核版权权限
                    authenticateRequired(Constant.Authentication.PERMISSION_VERIFY_RIGHT) {
                        //查询待审核版权
                        get("/verify") {
                            val pageNumberStr = call.request.queryParameters["page"]
                            val pageSize = Constant.DEFAULT_PAGE_SIZE

                            when {
                                //检查页码合法性
                                (pageNumberStr == null || !pageNumberStr.matches(Regex("[0-9]*"))) -> {
                                    call.httpRespond(HttpStatus.BAD_REQUEST with "无效的页码")
                                    return@get
                                }
                            }

                            val (count, pages) = pendingRightService.countPendingRights() to pendingRightService.getPendingRights(pageSize, pageNumberStr!!.toInt())

                            call.httpRespond(
                                data = mapOf(
                                    Constant.RespondField.COUNT to count,
                                    Constant.RespondField.RIGHT to pages
                                )
                            )
                        }

                        //通过待审核版权的申请
                        post("/verify/confirm") {
                            data class Form(
                                val rightId: Int
                            )

                            val targetRightId = call.receive<Form>().rightId
                            val targetRight = pendingRightService.getPendingRight(targetRightId.toLong())

                            when {
                                //检查版权存在性
                                (targetRight == null) -> {
                                    call.httpRespond(HttpStatus.NOT_FOUND with "无效的版权ID")
                                    return@post
                                }

                                //检查版权状态
                                (targetRight.status != PendingStatus.PENDING) -> {
                                    call.httpRespond(HttpStatus.FORBIDDEN with "非待审核版权")
                                    return@post
                                }
                            }

                            //获取钱包实体对象
                            val ownerWallet = walletService.getWallet(targetRight!!.deployer)

                            //获取pendingRight并生成部署表单
                            val deployForm = pendingRightService.convertToDeployForm(targetRight)

                            //获取估算Gas —— 估算Gas = 部署合约费用 + 调用Manager的Add Right函数费用
                            val estimateGas = rightService.estimateDeploy(ownerWallet!!.address, deployForm).toLong()

                            noticeService.insertNotice(
                                Notice(
                                    title = "版权审核通知",
                                    content = "您的版权申请:${targetRight.title}已通过审核,需要您填写区块链账户密码完成合约部署。",
                                    receiverId = ownerWallet.userId,
                                    targetRoute = "/chain/account"
                                )
                            )

                            //发送通知
                            when (pendingRightService.confirmPendingRight(targetRight.id, estimateGas * BlockChain.gasProvider.gasPrice.toLong())) {
                                true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                                false -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                            }
                        }

                        //拒绝待审核版权的申请
                        post("/verify/reject") {
                            data class Form(
                                val pendingRightId: Int,
                                val rejectReason: String
                            )

                            val form = call.receive<Form>()
                            val targetRightId = form.pendingRightId
                            val targetRight = pendingRightService.getPendingRight(targetRightId.toLong())

                            when {
                                //检查版权存在性
                                (targetRight == null) -> {
                                    call.httpRespond(HttpStatus.NOT_FOUND with "无效的版权ID")
                                    return@post
                                }

                                //检查版权状态
                                (targetRight.status != PendingStatus.PENDING) -> {
                                    call.httpRespond(HttpStatus.FORBIDDEN with "非待审核版权")
                                    return@post
                                }
                            }

                            val ownerWallet = walletService.getWallet(targetRight!!.deployer)

                            noticeService.insertNotice(
                                Notice(
                                    title = "版权审核通知",
                                    content = "您的版权申请:${targetRight.title}未通过审核,原因:${form.rejectReason}。",
                                    receiverId = ownerWallet!!.userId,
                                    targetRoute = "/chain/account"
                                )
                            )

                            //发送通知
                            when (pendingRightService.rejectPendingRight(targetRight.id, form.rejectReason)) {
                                true -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to true))
                                false -> call.httpRespond(data = mapOf(Constant.RespondField.SUCCESS to false))
                            }
                        }
                    }
                }
            }
        }
    }
}