package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.*
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightData
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.RightService
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.crypto.Credentials
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

class RightServiceImpl : RightService {
    override fun estimate(form: RightDeployForm): BigInteger {
        val binCode = Right.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string(form.title),
                string(form.registrationNumber),
                uint64(form.issueTime),
                uint64(form.expireTime),
                string(form.description)
            )
        )

        return Estimate.estimateDeploy("$binCode$encodedConstructor")
    }

    override fun addRight(transactionManager: TransactionManager, form: RightDeployForm): Right {
        val right = Right.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            DefaultGasProvider(),
            form.title,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description
        ).send()

        BlockChain.managerByBank!!.addRight(transactionManager.fromAddress,right.contractAddress).send()

        return right
    }

    override fun getRight(rightAddr: String): RightData {
        //创建函数调用交易
        val function = Function("serialize", emptyList(), listOf(TypeReference.makeTypeReference("string")))
        val transaction = Transaction.createFunctionCallTransaction(
            BlockChain.bankAddress,
            BlockChain.getNextNonce(BlockChain.bankAddress!!),
            BlockChain.gasProvider.gasPrice,
            BlockChain.gasProvider.gasLimit,
            rightAddr,
            BigInteger.ZERO,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).send().value

        //获取函数返回值
        val json = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        //解析json并返回
        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
    }

    override fun getRights(owner: String): List<RightData> {
        val rights = BlockChain.managerByBank?.getRights(owner)?.send()

        return rights?.map { getRight(it.toString()) } ?: emptyList()
    }
}