package moe._47saikyo.drm.blockchain.service.impl

import moe._47saikyo.drm.blockchain.annotation.ViewFunction
import moe._47saikyo.drm.blockchain.contract.DRManager.License
import moe._47saikyo.drm.blockchain.models.LicenseData
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import moe._47saikyo.drm.blockchain.models.ReceiptWrapper
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 基于Wrapper的LicenseService实现
 *
 * @author 刘一邦
 */
class LicenseWrapperService : LicenseService {
    private val logger = org.slf4j.LoggerFactory.getLogger(LicenseWrapperService::class.java)
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)
    private val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)

    override fun estimateDeploy(
        callerAddr: String,
        form: LicenseDeployForm
    ): BigInteger {
//        val binCode = License.BINARY
//
//        val encodedConstructor = FunctionEncoder.encodeConstructor(
//            listOf(
//                string(form.rightTitle),
//                address(form.rightAddr),
//                string(form.owner),
//                uint64(form.issueTime),
//                uint64(form.expireTime),
//                string(form.description)
//            )
//        )
//
//        return Estimate.estimateDeploy(callerAddr, "$binCode$encodedConstructor")
//            .add(BlockChainConstant.Gas.MANAGER_ADD)
//            .add(BlockChainConstant.Gas.RIGHT_ADD)

        return BigInteger.valueOf(0)
    }

    override fun addLicense(
        transactionManager: TransactionManager,
        form: LicenseDeployForm
    ): ReceiptWrapper<LicenseData>? {
        val license = License(
            BigInteger.valueOf(0),
            form.rightTitle,
            form.rightKeyPairData.toKeyPairStruct(),
            transactionManager.fromAddress,
            form.owner,
            form.issueTime,
            form.expireTime,
            form.description
        )

        val receipt = managerService.addLicense(transactionManager, license)

        val lastLicense =
            LicenseData.fromLicenseStruct(
                managerService.getLastLicense(transactionManager.fromAddress, transactionManager.fromAddress)
            )

        return if (lastLicense == LicenseData.fromLicenseStruct(license))
            ReceiptWrapper(receipt, lastLicense)
        else
            null
    }

    @ViewFunction
    override fun getLicense(callerAddr: String, deployer: String, index: Number): LicenseData {
        val license = managerService.getLicense(callerAddr, deployer, index)
        logger.info("getLicense[$license]")
        return LicenseData.fromLicenseStruct(license)
    }

    @ViewFunction
    override fun getLicenses(callerAddr: String, deployer: String): List<LicenseData> {
        val licenses = managerService.getLicenses(callerAddr, deployer)
        logger.info("getLicenses[$licenses]")
        return licenses.map { LicenseData.fromLicenseStruct(it) }
    }
}