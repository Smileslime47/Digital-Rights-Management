package moe._47saikyo.drm.backend.dao.impl

import moe._47saikyo.drm.backend.dao.ReceiptDao
import moe._47saikyo.drm.backend.mapper.ReceiptTable
import moe._47saikyo.drm.core.domain.Receipt
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ReceiptDaoImpl : ReceiptDao {
    override suspend fun getReceipt(where: SqlExpressionBuilder.() -> Op<Boolean>): Receipt? =
        transaction { ReceiptTable.select(where).map(ReceiptTable::resultRowToReceipt).singleOrNull() }

    override suspend fun getReceipts(): List<Receipt> =
        transaction { ReceiptTable.selectAll().map(ReceiptTable::resultRowToReceipt) }

    override suspend fun insertReceipt(receipt: Receipt): Receipt? =
        transaction {
            try {
                ReceiptTable.insert(ReceiptTable.getStatementBinder(receipt)).resultedValues?.singleOrNull()
                    ?.let(ReceiptTable::resultRowToReceipt)
            } catch (e: ExposedSQLException) {
                null
            }
        }

    override suspend fun updateReceipt(receipt: Receipt): Boolean =
        transaction { ReceiptTable.update({ ReceiptTable.id eq receipt.id }, null, ReceiptTable.getStatementBinder(receipt)) > 0 }

    override suspend fun deleteReceipt(receipt: Receipt): Boolean =
        transaction { ReceiptTable.deleteWhere { id eq receipt.id } > 0 }
}