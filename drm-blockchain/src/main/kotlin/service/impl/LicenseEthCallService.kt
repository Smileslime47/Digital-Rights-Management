package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.*
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.License
import moe._47saikyo.models.LicenseData
import moe._47saikyo.models.LicenseDeployForm
import moe._47saikyo.service.LicenseService
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.tx.TransactionManager
import java.math.BigInteger

@Deprecated("Use LicenseWrapperService instead,maintenance only.")

class LicenseEthCallService: LicenseService {
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)
    private val rightService: RightService by KoinJavaComponent.inject(RightService::class.java)
    private val logger = org.slf4j.LoggerFactory.getLogger(LicenseEthCallService::class.java)

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
        ).sendAsync().get()

        logger.info("Trying to add license to right...")
        rightService.addLicense(transactionManager,form.rightAddr,license.contractAddress)
        logger.info("Trying to add license to manager...")
        managerService.addLicense(transactionManager, license)
        logger.info("Add finished")

        return license
    }

    @ViewFunction
    override fun getPureData(
        callerAddr: String,
        licenseAddr: String
    ): LicenseData {
        //创建函数调用交易
        val function = Function("serialize", emptyList(), listOf(TypeReference.create(string::class.java)))
        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            licenseAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val json = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        //解析json并返回
        return jacksonObjectMapper().readerFor(LicenseData::class.java).readValue(json)
    }

    @ViewFunction
    override fun getLicenses(owner: String): List<LicenseData> {
        val addrs = managerService.getLicenses(owner)
        logger.info("getLicenses[$addrs]")
        return addrs.map { getPureData(owner, it) }
    }
}