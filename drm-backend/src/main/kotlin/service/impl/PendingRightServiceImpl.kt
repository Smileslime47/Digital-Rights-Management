package moe._47saikyo.service.impl

import moe._47saikyo.contract.Right
import moe._47saikyo.dao.PendingRightDao
import moe._47saikyo.mapper.PendingRightTable
import domain.PendingRight
import domain.PendingStatus
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.PendingRightService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.tx.TransactionManager
import java.math.BigInteger

class PendingRightServiceImpl : PendingRightService {
    private val pendingRightDao: PendingRightDao by KoinJavaComponent.inject(PendingRightDao::class.java)
    private val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)
    override suspend fun getPendingRight(id: Long): PendingRight? =
        pendingRightDao.getPendingRight { PendingRightTable.id eq id }


    override suspend fun getPendingRights(): List<PendingRight> =
        pendingRightDao.getPendingRights()

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

    override suspend fun confirmPendingRight(id: Long): Boolean {
        val right = getPendingRight(id)
        if (right?.status == PendingStatus.PENDING) {
            right.status = PendingStatus.CONFIRMED
            return pendingRightDao.updatePendingRight(right)
        } else {
            return false
        }
    }

    override suspend fun rejectPendingRight(id: Long): Boolean {
        val right = getPendingRight(id)
        if (right?.status == PendingStatus.PENDING) {
            right.status = PendingStatus.REJECTED
            return pendingRightDao.updatePendingRight(right)
        } else {
            return false
        }
    }
}