package moe._47saikyo.service.impl

import moe._47saikyo.constant.GlobalConstant
import moe._47saikyo.contract.Right
import moe._47saikyo.dao.PendingRightDao
import moe._47saikyo.domain.PendingRight
import moe._47saikyo.enums.PendingStatus
import moe._47saikyo.mapper.PendingRightTable
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.PendingRightService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent.inject
import org.web3j.tx.TransactionManager

/**
 * PendingRightService实现
 *
 * @author 刘一邦
 */
class PendingRightServiceImpl : PendingRightService {
    private val pendingRightDao: PendingRightDao by inject(PendingRightDao::class.java)
    private val rightService: RightService by inject(RightService::class.java)

    override fun convertToDeployForm(pendingRight: PendingRight): RightDeployForm =
        RightDeployForm(
            title = pendingRight.title,
            owner = pendingRight.owner,
            registrationNumber = pendingRight.registrationNumber,
            issueTime = pendingRight.issueTime.toBigInteger(),
            expireTime = pendingRight.expireTime.toBigInteger(),
            description = pendingRight.description ?: GlobalConstant.EMPTY_STRING,
            fileName = pendingRight.fileName,
            fileHash = pendingRight.fileHash
        )

    override suspend fun getPendingRight(id: Long): PendingRight? =
        pendingRightDao.getPendingRight { PendingRightTable.id eq id }

    override suspend fun countPendingRights(): Long =
        pendingRightDao.countPendingRights { PendingRightTable.status eq PendingStatus.PENDING.toString() }

    override suspend fun getPendingRights(pageSize: Int, pageNumber: Int): List<PendingRight> =
        pendingRightDao.getPendingRights(pageSize, pageNumber) { PendingRightTable.status eq PendingStatus.PENDING.toString() }

    override suspend fun countPendingRights(address: String): Long =
        pendingRightDao.countPendingRights { PendingRightTable.deployer eq address }

    override suspend fun getPendingRights(pageSize: Int, pageNumber: Int, address: String): List<PendingRight> =
        pendingRightDao.getPendingRights(pageSize, pageNumber) { PendingRightTable.deployer eq address }

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
            pendingRightDao.updatePendingRight(pendingRight.apply { status = PendingStatus.DEPLOYING })

            val form = convertToDeployForm(pendingRight)

            val right = rightService.addRight(transactionManager, form)

            return if (right.isValid) {
                if (pendingRightDao.updatePendingRight(pendingRight.apply { status = PendingStatus.DEPLOYED })) right else null
            } else {
                if (pendingRightDao.updatePendingRight(pendingRight.apply { status = PendingStatus.CONFIRMED })) right else null
            }
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