package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.*
import moe._47saikyo.annotation.TxManagerPlaceholder
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.models.LicenseData
import moe._47saikyo.models.LicenseDeployForm
import moe._47saikyo.models.RightData
import moe._47saikyo.service.LicenseService
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 基于Wrapper的LicenseService实现
 *
 * @author 刘一邦
 */
@Deprecated("Use LicenseWrapperService instead,maintenance only.")
class LicenseWrapperService : LicenseService {
    private val managerService: ManagerService by KoinJavaComponent.inject(LicenseWrapperService::class.java)
    private val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)

    override fun estimateDeploy(
        callerAddr: String,
        form: LicenseDeployForm
    ): BigInteger {
        val binCode = License.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string(form.rightTitle),
                address(form.rightAddr),
                string(form.owner),
                uint64(form.issueTime),
                uint64(form.expireTime),
                string(form.description)
            )
        )

        return Estimate.estimateDeploy(callerAddr, "$binCode$encodedConstructor")
            .add(BlockChainConstant.Gas.MANAGER_ADD)
            .add(BlockChainConstant.Gas.RIGHT_ADD)
    }

    override fun addLicense(
        transactionManager: TransactionManager,
        form: LicenseDeployForm
    ): License {
        val license = License.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider,
            form.rightTitle,
            form.rightAddr,
            form.owner,
            form.issueTime,
            form.expireTime,
            form.description
        ).send()

        rightService.addLicense(transactionManager,form.rightAddr,license.contractAddress)
        managerService.addLicense(transactionManager, license)

        return license
    }

    @ViewFunction
    override fun getPureData(
        callerAddr: String,
        licenseAddr: String
    ): LicenseData {
        //创建函数调用交易
        val license = License.load(
            licenseAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )
        val json = license.serialize().send()
        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
    }

    @ViewFunction
    override fun getLicenses(owner: String): List<LicenseData> =
        managerService.getLicenses(owner).map { getPureData(owner, it) }
}