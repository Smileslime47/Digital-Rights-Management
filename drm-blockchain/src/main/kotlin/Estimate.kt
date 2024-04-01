package moe._47saikyo

import org.web3j.abi.FunctionEncoder
import org.web3j.protocol.core.methods.request.Transaction
import java.math.BigInteger

/**
 * 估算合约所需的gas
 *
 * @author 刘一邦
 */
object Estimate {
    /**
     * 估算合约所需的gas
     *
     * @param from 发送者地址
     * @param gasPrice gas价格
     * @param gasLimit gas限制
     * @param binCode 合约二进制代码[+构造器二进制代码]
     * @return 估算的gas
     *
     * @see [FunctionEncoder.encodeConstructor]
     */
    fun estimateDeploy(from:String, gasPrice: BigInteger, gasLimit: BigInteger, binCode:String): BigInteger {
//        String encodedConstructor =
//        FunctionEncoder.encodeConstructor(Arrays.asList(new Type(value), ...));

        val transaction = Transaction.createContractTransaction(
            from,
            BlockChain.getNextNonce(from),
            gasPrice,
            gasLimit,
            BigInteger.valueOf(0x0),
            "0x$binCode"
        )

        val estimateGas = BlockChain.web3jInstance!!.ethEstimateGas(transaction).send()
        return estimateGas.amountUsed
    }
}