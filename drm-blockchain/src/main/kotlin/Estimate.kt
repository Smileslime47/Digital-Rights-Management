package moe._47saikyo

import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigInteger

/**
 * 估算合约所需的gas
 *
 * @author 刘一邦
 */
object Estimate {
    /**
     * 基于银行账户估算部署合约所需的gas
     * 由于合约部署消耗的gas只取决于合约的二进制代码，因此只需要提供合约的二进制代码即可
     *
     * @param binCode 合约二进制代码[+构造器二进制代码]
     * @return 估算的gas,单位为wei
     *
     * @see [FunctionEncoder.encodeConstructor]
     */
    fun estimateDeploy(binCode: String): BigInteger {
        val transaction = Transaction.createContractTransaction(
            BlockChain.bankAddress,
            BlockChain.getNextNonce(BlockChain.bankAddress!!),
            BlockChain.gasProvider.gasPrice,
            BlockChain.gasProvider.gasLimit,
            BigInteger.valueOf(0x0),
            "0x$binCode"
        )

        val estimateGas = BlockChain.web3jInstance!!.ethEstimateGas(transaction).send()
        return estimateGas.amountUsed
    }

    /**
     * 基于银行账户估算调用合约函数所需的gas
     * 由于合约函数调用消耗的gas只取决于函数的参数，因此只需要提供合约地址、函数名、参数和参数类型即可
     *
     * @param contractAddress 合约地址
     * @param functionName 函数名
     * @param params 函数参数
     * @param paramTypes 函数参数类型
     * @return 估算的gas,单位为wei
     *
     * @see [FunctionEncoder.encode]
     */
    fun estimateCall(contractAddress: String, functionName: String, params: List<Type<*>>, paramTypes: List<TypeReference<*>>): BigInteger {
        val function = Function(functionName, params, paramTypes)
        val transaction = Transaction.createFunctionCallTransaction(
            BlockChain.bankAddress,
            BlockChain.getNextNonce(BlockChain.bankAddress!!),
            BlockChain.gasProvider.gasPrice,
            BlockChain.gasProvider.gasLimit,
            contractAddress,
            FunctionEncoder.encode(function)
        )

        val estimateGas = BlockChain.web3jInstance!!.ethEstimateGas(transaction).send()
        return estimateGas.amountUsed
    }
}