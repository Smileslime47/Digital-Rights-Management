package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.*
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightData
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import org.koin.java.KoinJavaComponent.inject
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.tx.TransactionManager
import java.math.BigInteger

@Deprecated("Use RightWrapperService instead, maintenance only.")

class RightEthCallService : RightService {
    private val managerService: ManagerService by inject(ManagerService::class.java)
    private val logger = org.slf4j.LoggerFactory.getLogger(RightEthCallService::class.java)

    override fun searchByTitle(
        callerAddr: String,
        title: String
    ): List<String> =
        managerService.searchByTitle(callerAddr, title)


    override fun estimateDeploy(
        callerAddr: String,
        form: RightDeployForm
    ): BigInteger {
        val binCode = Right.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string(form.title),
                string(form.owner),
                string(form.registrationNumber),
                uint64(form.issueTime),
                uint64(form.expireTime),
                string(form.description),
                string(form.fileName),
                string(form.fileHash)
            )
        )

        return Estimate.estimateDeploy(callerAddr, "$binCode$encodedConstructor")
            .add(BlockChainConstant.Gas.MANAGER_ADD)
    }

    override fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): Right {
        val right = Right.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider,
            form.title,
            form.owner,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description,
            form.fileName,
            form.fileHash
        ).sendAsync().get()

        managerService.addRight(transactionManager, right)

        return right
    }

    override fun addLicense(
        transactionManager: TransactionManager,
        rightAddr: String,
        licenseAddr: String
    ) {
        val right = Right.load(
            rightAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        right.addLicense(licenseAddr).send()
    }

    @ViewFunction
    override fun getPureData(
        callerAddr: String,
        rightAddr: String
    ): RightData {
        //创建函数调用交易
        val function = Function("serialize", emptyList(), listOf(TypeReference.create(string::class.java)))
        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            rightAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val json = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        //解析json并返回
        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
    }

    @ViewFunction
    override fun getRights(owner: String): List<RightData> {
        val addrs = managerService.getRights(owner)
        logger.info("getRights[$addrs]")
        return addrs.map { getPureData(owner, it) }
    }
}