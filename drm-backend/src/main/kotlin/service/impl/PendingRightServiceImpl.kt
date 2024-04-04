package moe._47saikyo.service.impl

import domain.PendingRight
import enums.PendingStatus
import moe._47saikyo.BlockChain
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.Right
import moe._47saikyo.dao.PendingRightDao
import moe._47saikyo.mapper.PendingRightTable
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.PendingRightService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * PendingRightService实现
 *
 * @author 刘一邦
 */
class PendingRightServiceImpl : PendingRightService {
    private val pendingRightDao: PendingRightDao by KoinJavaComponent.inject(PendingRightDao::class.java)
    private val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)

    override fun convertToDeployForm(pendingRight: PendingRight): RightDeployForm =
        RightDeployForm(
            title = pendingRight.title,
            registrationNumber = pendingRight.registrationNumber,
            issueTime = pendingRight.issueTime.toBigInteger(),
            expireTime = pendingRight.expireTime.toBigInteger(),
            description = pendingRight.description
        )

    override suspend fun getPendingRight(id: Long): PendingRight? =
        pendingRightDao.getPendingRight { PendingRightTable.id eq id }

    override suspend fun countPendingRights(): Long =
        pendingRightDao.countPendingRights { PendingRightTable.status eq PendingStatus.PENDING.toString() }

    override suspend fun getPendingRights(pageSize: Int, pageNumber: Int): List<PendingRight> =
        pendingRightDao.getPendingRights(pageSize, pageNumber) { PendingRightTable.status eq PendingStatus.PENDING.toString() }

    override suspend fun countPendingRights(address: String): Long =
        pendingRightDao.countPendingRights { PendingRightTable.owner eq address }

    override suspend fun getPendingRights(pageSize: Int, pageNumber: Int, address: String): List<PendingRight> =
        pendingRightDao.getPendingRights(pageSize, pageNumber) { PendingRightTable.owner eq address }

    override suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight? =
        pendingRightDao.insertPendingRight(pendingRight)

    override suspend fun confirmPendingRight(id: Long, estimatePrice: Long): Boolean {
        val right = getPendingRight(id)
        if (right?.status == PendingStatus.PENDING) {
            right.status = PendingStatus.CONFIRMED
            right.estimatePrice = estimatePrice
            right.comment = "已审核通过，请在确认部署价格后输入密码并确认部署。"
            return pendingRightDao.updatePendingRight(right)
        } else {
            return false
        }
    }

    override suspend fun deployPendingRight(id: Long, transactionManager: TransactionManager): Right? {
        val pendingRight = getPendingRight(id)
        if (pendingRight?.status == PendingStatus.CONFIRMED) {
            val form = RightDeployForm(
                title = pendingRight.title,
                registrationNumber = pendingRight.registrationNumber,
                issueTime = BigInteger.valueOf(pendingRight.issueTime),
                expireTime = BigInteger.valueOf(pendingRight.expireTime),
                description = pendingRight.description
            )
            val right = rightService.addRight(transactionManager, form)

            pendingRight.status = PendingStatus.DEPLOYED
            pendingRightDao.updatePendingRight(pendingRight)

            return right
        } else {
            return null
        }
    }

    override suspend fun rejectPendingRight(id: Long, rejectReason: String): Boolean {
        val right = getPendingRight(id)
        if (right?.status == PendingStatus.PENDING) {
            right.status = PendingStatus.REJECTED
            right.comment = rejectReason
            return pendingRightDao.updatePendingRight(right)
        } else {
            return false
        }
    }
}