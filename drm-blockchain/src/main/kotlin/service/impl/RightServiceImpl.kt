package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.BlockChain
import moe._47saikyo.Estimate
import moe._47saikyo.annotation.TxManagerPlaceholder
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightData
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import moe._47saikyo.string
import moe._47saikyo.uint64
import org.koin.java.KoinJavaComponent.inject
import org.web3j.abi.FunctionEncoder
import org.web3j.tx.TransactionManager
import java.math.BigInteger

class RightServiceImpl : RightService {
    private val managerService: ManagerService by inject(ManagerService::class.java)
    override fun searchByTitle(title: String): List<String> =
        managerService.searchByTitle(title)


    override fun estimateDeploy(form: RightDeployForm): BigInteger {
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

        return Estimate.estimateDeploy("$binCode$encodedConstructor")
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
        ).send()

        managerService.addRight(transactionManager, right)

        return right
    }

    @ViewFunction
    override fun getPureData(
        rightAddr: String
    ): RightData {
        val right = Right.load(
            rightAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )
        val json = right.serialize().send()
        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
//        //创建函数调用交易
//        val function = Function("serialize", emptyList(), listOf(TypeReference.makeTypeReference("string")))
//        val transaction = Transaction.createFunctionCallTransaction(
//            BlockChain.bankAddress,
//            BlockChain.getNextNonce(BlockChain.bankAddress!!),
//            BlockChain.gasProvider.gasPrice,
//            BlockChain.gasProvider.gasLimit,
//            rightAddr,
//            FunctionEncoder.encode(function)
//        )
//
//        //发送交易并获取结果
//        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).send().value
//
//        //获取函数返回值
//        val json = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String
//
//        //解析json并返回
//        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
    }

    @ViewFunction
    override fun getRights(owner: String): List<RightData> =
        managerService.getRights(owner).map { getPureData(it) }
}