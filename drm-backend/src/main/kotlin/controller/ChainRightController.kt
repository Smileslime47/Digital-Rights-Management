package moe._47saikyo.controller

import domain.Group
import domain.Notice
import domain.PendingRight
import enums.PendingStatus
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import moe._47saikyo.BlockChain
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.constant.Constant
import moe._47saikyo.contract.DRManager
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
    val noticeService: NoticeService by inject(NoticeService::class.java)

    routing {
        route("/chain/right") {
            get {
                val addr = call.parameters["addr"]

                when {
                    //addr为空
                    (addr == null) -> {
                        call.httpRespond(HttpStatus.BAD_REQUEST with "无效的地址")
                        return@get
                    }
                }

                val rights = rightService.getRights(addr!!)

                call.httpRespond(
                    data = mapOf(
                        Constant.RespondField.RIGHT to rights
                    )
                )
            }
        }
    }
}