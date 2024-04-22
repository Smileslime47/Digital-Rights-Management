package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.PendingRightDao
import moe._47saikyo.drm.backend.mapper.PendingRightTable
import moe._47saikyo.drm.backend.service.PendingRightService
import moe._47saikyo.drm.backend.service.ReceiptService
import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.blockchain.models.RightData
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import moe._47saikyo.drm.blockchain.service.RightService
import moe._47saikyo.drm.core.constant.GlobalConstant
import moe._47saikyo.drm.core.domain.PendingRight
import moe._47saikyo.drm.core.enums.PendingStatus
import org.jetbrains.exposed.sql.and
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.tx.TransactionManager

/**
 * PendingRightService实现
 *
 * @author 刘一邦
 */
class PendingRightServiceImpl : PendingRightService {
    private val logger = LoggerFactory.getLogger(PendingRightServiceImpl::class.java)
    private val pendingRightDao: PendingRightDao by inject(PendingRightDao::class.java)
    private val rightService: RightService by inject(RightService::class.java)
    private val receiptService: ReceiptService by inject(ReceiptService::class.java)

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

    override suspend fun deployPendingRight(id: Long, transactionManager: TransactionManager): RightData? {
        val pendingRight = getPendingRight(id)
        if (pendingRight?.status == PendingStatus.CONFIRMED) {
            try {
                pendingRightDao.updatePendingRight(pendingRight.apply { status = PendingStatus.DEPLOYING })

                val form = convertToDeployForm(pendingRight)

                val receiptWrapper = rightService.addRight(transactionManager, form)
                val right = receiptWrapper?.getPayload()
                val receipt = receiptWrapper?.getReceipt(right!!.deployer, right.index)

                receiptService.insertReceipt(receipt!!)
                pendingRightDao.updatePendingRight(pendingRight.apply {
                    status = PendingStatus.DEPLOYED
                })
                return right
            } catch (e: Exception) {
                logger.error(e.message)
                pendingRightDao.updatePendingRight(pendingRight.apply {
                    status = PendingStatus.CONFIRMED
                })
                return null
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