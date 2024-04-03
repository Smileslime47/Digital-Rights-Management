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
     * 基于银行账户估算部署合约所需的gas
     * 由于合约部署消耗的gas只取决于合约的二进制代码，因此只需要提供合约的二进制代码即可
     *
     * @param binCode 合约二进制代码[+构造器二进制代码]
     * @return 估算的gas
     *
     * @see [FunctionEncoder.encodeConstructor]
     */
    fun estimateDeploy(binCode:String): BigInteger {
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
}