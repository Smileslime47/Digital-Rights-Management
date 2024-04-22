package moe._47saikyo.drm.backend.dao

import moe._47saikyo.drm.core.domain.Receipt
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

/**
 * ReceiptDao接口
 *
 * @author 刘一邦
 */
interface ReceiptDao {
    suspend fun getReceipt(where: SqlExpressionBuilder.() -> Op<Boolean>): Receipt?
    suspend fun getReceipts(): List<Receipt>
    suspend fun insertReceipt(receipt: Receipt): Receipt?
    suspend fun updateReceipt(receipt: Receipt): Boolean
    suspend fun deleteReceipt(receipt: Receipt): Boolean
}