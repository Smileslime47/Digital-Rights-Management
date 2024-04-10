package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.BlockChain
import moe._47saikyo.Estimate
import moe._47saikyo.address
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
import moe._47saikyo.uint64
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.tx.TransactionManager
import java.math.BigInteger

class LicenseServiceImpl : LicenseService {
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)

    override fun estimateDeploy(form: LicenseDeployForm): BigInteger {
        val binCode = License.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                address(form.right),
                uint64(form.issueTime),
                uint64(form.expireTime),
            )
        )

        return Estimate.estimateDeploy("$binCode$encodedConstructor")
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
            form.right,
            form.issueTime,
            form.expireTime
        ).send()

        val right = Right.load(
            form.right,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        right.addLicense(license.contractAddress).send()
        managerService.addLicense(transactionManager, license)

        return license
    }

    @ViewFunction
    override fun getPureData(
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
        managerService.getLicenses(owner).map { getPureData(it) }
}