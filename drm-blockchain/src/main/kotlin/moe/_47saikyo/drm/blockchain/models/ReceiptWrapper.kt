package moe._47saikyo.drm.blockchain.models

import moe._47saikyo.drm.core.domain.Receipt
import org.web3j.protocol.core.methods.response.TransactionReceipt

/**
 * 交易回执包装类
 *
 * @author 刘一邦
 */
data class ReceiptWrapper<T>(
    private val receipt: TransactionReceipt,
    private val payload: T,
) {
    fun getPayload(): T = payload
    fun getReceipt(deployer: String, deployedIndex: Long): Receipt {
        return Receipt(
            id = 0,
            status = receipt.status,
            transactionHash = receipt.transactionHash,
            blockHash = receipt.blockHash,
            blockNumber = receipt.blockNumber.toLong(),
            gasUsed = receipt.gasUsed.toLong(),
            deployer = deployer,
            deployIndex = deployedIndex,
            createTime = System.currentTimeMillis()
        )
    }
}
