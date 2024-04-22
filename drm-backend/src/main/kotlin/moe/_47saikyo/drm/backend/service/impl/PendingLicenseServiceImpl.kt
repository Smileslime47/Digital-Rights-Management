package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.PendingLicenseDao
import moe._47saikyo.drm.backend.mapper.PendingLicenseTable
import moe._47saikyo.drm.backend.service.PendingLicenseService
import moe._47saikyo.drm.backend.service.ReceiptService
import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.blockchain.models.LicenseData
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.core.constant.GlobalConstant
import moe._47saikyo.drm.core.domain.PendingLicense
import moe._47saikyo.drm.core.domain.Receipt
import moe._47saikyo.drm.core.enums.PendingStatus
import org.jetbrains.exposed.sql.and
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.tx.TransactionManager

class PendingLicenseServiceImpl : PendingLicenseService {
    private val logger = LoggerFactory.getLogger(PendingLicenseServiceImpl::class.java)
    private val pendingLicenseDao: PendingLicenseDao by inject(PendingLicenseDao::class.java)
    private val licenseService: LicenseService by inject(LicenseService::class.java)
    private val receiptService: ReceiptService by inject(ReceiptService::class.java)

    override fun convertToDeployForm(pendingLicense: PendingLicense): LicenseDeployForm {
        val rightKeyPair = KeyPairData(
            deployer = pendingLicense.rightDeployer,
            arrayIndex = pendingLicense.rightIndex,
        )
        return LicenseDeployForm(
            rightTitle = pendingLicense.rightTitle,
            rightKeyPairData = rightKeyPair,
            owner = pendingLicense.owner,
            issueTime = pendingLicense.issueTime.toBigInteger(),
            expireTime = pendingLicense.expireTime.toBigInteger(),
            description = pendingLicense.description ?: GlobalConstant.EMPTY_STRING,
        )
    }


    override suspend fun getPendingLicense(id: Long): PendingLicense? = pendingLicenseDao.getPendingLicense { PendingLicenseTable.id eq id }

    override suspend fun countPendingLicensesOfRight(rightKey: KeyPairData): Long = pendingLicenseDao.countPendingLicenses {
        (PendingLicenseTable.right_deployer eq rightKey.deployer) and (PendingLicenseTable.right_index eq rightKey.arrayIndex)
    }

    override suspend fun countPendingLicensesOfRight(rightKey: KeyPairData, licenseDeployerAddr: String): Long = pendingLicenseDao.countPendingLicenses {
        (PendingLicenseTable.right_deployer eq rightKey.deployer) and (PendingLicenseTable.right_index eq rightKey.arrayIndex) and (PendingLicenseTable.deployer eq licenseDeployerAddr)
    }

    override suspend fun getPendingLicensesOfRight(rightKey: KeyPairData, pageSize: Int, pageNumber: Int): List<PendingLicense> = pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) {
        (PendingLicenseTable.right_deployer eq rightKey.deployer) and (PendingLicenseTable.right_index eq rightKey.arrayIndex)
    }

    override suspend fun getPendingLicenseOfRight(rightKey: KeyPairData, licenseDeployerAddr: String, pageSize: Int, pageNumber: Int): List<PendingLicense> =
        pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) {
            (PendingLicenseTable.right_deployer eq rightKey.deployer) and (PendingLicenseTable.right_index eq rightKey.arrayIndex) and (PendingLicenseTable.deployer eq licenseDeployerAddr)
        }

    override suspend fun countPendingLicenseOfUser(address: String): Long = pendingLicenseDao.countPendingLicenses { PendingLicenseTable.deployer eq address }

    override suspend fun getPendingLicensesOfUser(address: String, pageSize: Int, pageNumber: Int): List<PendingLicense> =
        pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) { PendingLicenseTable.deployer eq address }

    override suspend fun insertPendingLicense(pendingLicense: PendingLicense): PendingLicense? = pendingLicenseDao.insertPendingLicense(pendingLicense)

    override suspend fun confirmPendingLicense(id: Long, estimatePrice: Long): Boolean {
        val license = getPendingLicense(id)
        if (license?.status == PendingStatus.PENDING) {
            license.status = PendingStatus.CONFIRMED
            license.estimatePrice = estimatePrice
            license.comment = "已审核通过，请在确认部署价格后输入密码并确认部署。"
            return pendingLicenseDao.updatePendingLicense(license)
        } else {
            return false
        }
    }

    override suspend fun deployPendingLicense(id: Long, transactionManager: TransactionManager): LicenseData? {
        val pendingLicense = getPendingLicense(id)
        if (pendingLicense?.status == PendingStatus.CONFIRMED) {
            try {
                pendingLicenseDao.updatePendingLicense(pendingLicense.apply { status = PendingStatus.DEPLOYING })

                val form = convertToDeployForm(pendingLicense)

                val receiptWrapper = licenseService.addLicense(transactionManager, form)
                val license = receiptWrapper?.getPayload()
                val receipt = receiptWrapper?.getReceipt(license!!.deployer, license.index)

                receiptService.insertReceipt(receipt!!)
                pendingLicenseDao.updatePendingLicense(pendingLicense.apply {
                    status = PendingStatus.DEPLOYED
                })
                return license
            } catch (e: Exception) {
                logger.error(e.message)
                pendingLicenseDao.updatePendingLicense(pendingLicense.apply {
                    status = PendingStatus.CONFIRMED
                })
                return null
            }

        } else {
            return null
        }
    }

    override suspend fun rejectPendingLicense(id: Long, rejectReason: String): Boolean {
        val license = getPendingLicense(id)
        if (license?.status == PendingStatus.PENDING) {
            license.status = PendingStatus.REJECTED
            license.comment = rejectReason
            return pendingLicenseDao.updatePendingLicense(license)
        } else {
            return false
        }
    }
}