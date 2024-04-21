package moe._47saikyo.service.impl

import moe._47saikyo.constant.GlobalConstant
import moe._47saikyo.contract.License
import moe._47saikyo.dao.PendingLicenseDao
import moe._47saikyo.domain.PendingLicense
import moe._47saikyo.enums.PendingStatus
import moe._47saikyo.mapper.PendingLicenseTable
import moe._47saikyo.models.LicenseDeployForm
import moe._47saikyo.service.LicenseService
import moe._47saikyo.service.PendingLicenseService
import org.jetbrains.exposed.sql.and
import org.koin.java.KoinJavaComponent.inject
import org.web3j.tx.TransactionManager

class PendingLicenseServiceImpl : PendingLicenseService {
    private val pendingLicenseDao: PendingLicenseDao by inject(PendingLicenseDao::class.java)
    private val licenseService: LicenseService by inject(LicenseService::class.java)

    override fun convertToDeployForm(pendingLicense: PendingLicense): LicenseDeployForm =
        LicenseDeployForm(
            rightTitle = pendingLicense.rightTitle,
            rightAddr = pendingLicense.rightAddr,
            owner = pendingLicense.owner,
            issueTime = pendingLicense.issueTime.toBigInteger(),
            expireTime = pendingLicense.expireTime.toBigInteger(),
            description = pendingLicense.description ?: GlobalConstant.EMPTY_STRING,
        )

    override suspend fun getPendingLicense(id: Long): PendingLicense? =
        pendingLicenseDao.getPendingLicense { PendingLicenseTable.id eq id }

    override suspend fun countPendingLicensesOfRight(rightAddr: String): Long =
        pendingLicenseDao.countPendingLicenses { PendingLicenseTable.right_addr eq rightAddr }

    override suspend fun countPendingLicensesOfRight(rightAddr: String, licenseDeployerAddr: String): Long =
        pendingLicenseDao.countPendingLicenses {
            (PendingLicenseTable.right_addr eq rightAddr) and (PendingLicenseTable.deployer eq licenseDeployerAddr)
        }

    override suspend fun getPendingLicensesOfRight(rightAddr: String, pageSize: Int, pageNumber: Int): List<PendingLicense> =
        pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) { PendingLicenseTable.right_addr eq rightAddr }

    override suspend fun getPendingLicenseOfRight(rightAddr: String, licenseDeployerAddr: String, pageSize: Int, pageNumber: Int): List<PendingLicense> =
        pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) {
            (PendingLicenseTable.right_addr eq rightAddr) and (PendingLicenseTable.deployer eq licenseDeployerAddr)
        }

    override suspend fun countPendingLicenseOfUser(address: String): Long =
        pendingLicenseDao.countPendingLicenses { PendingLicenseTable.deployer eq address }

    override suspend fun getPendingLicensesOfUser(address: String, pageSize: Int, pageNumber: Int): List<PendingLicense> =
        pendingLicenseDao.getPendingLicenses(pageSize, pageNumber) { PendingLicenseTable.deployer eq address }

    override suspend fun insertPendingLicense(pendingLicense: PendingLicense): PendingLicense? =
        pendingLicenseDao.insertPendingLicense(pendingLicense)

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

    override suspend fun deployPendingLicense(id: Long, transactionManager: TransactionManager): License? {
        val pendingLicense = getPendingLicense(id)
        if (pendingLicense?.status == PendingStatus.CONFIRMED) {
            pendingLicenseDao.updatePendingLicense(pendingLicense.apply { status = PendingStatus.DEPLOYING })

            val form = convertToDeployForm(pendingLicense)

            val license = licenseService.addLicense(transactionManager, form)

            return if (license.isValid) {
                if (pendingLicenseDao.updatePendingLicense(pendingLicense.apply { status = PendingStatus.DEPLOYED })) license else null
            } else {
                if (pendingLicenseDao.updatePendingLicense(pendingLicense.apply { status = PendingStatus.CONFIRMED })) license else null
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