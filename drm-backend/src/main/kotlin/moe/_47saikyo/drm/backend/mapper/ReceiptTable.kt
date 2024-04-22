package moe._47saikyo.drm.backend.mapper

import moe._47saikyo.drm.core.domain.Receipt
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

object ReceiptTable : Table("drm_receipt") {
    val id = long("id").autoIncrement()
    val status = varchar("status", 128)
    val transaction_hash = varchar("transaction_hash", 128)
    val block_hash = varchar("block_hash", 128)
    val block_number = long("block_number")
    val gas_used = long("gas_used")
    val deployer = varchar("deployer", 128)
    val deploy_index = long("deploy_index")
    val create_time = long("create_time")
    override val primaryKey = PrimaryKey(id)

    fun resultRowToReceipt(row: ResultRow) = Receipt(
        id = row[id],
        status = row[status],
        transactionHash = row[transaction_hash],
        blockHash = row[block_hash],
        blockNumber = row[block_number],
        gasUsed = row[gas_used],
        deployer = row[deployer],
        deployIndex = row[deploy_index],
        createTime = row[create_time]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(receipt: Receipt): ReceiptTable.(T) -> Unit = {
        it[status] = receipt.status
        it[transaction_hash] = receipt.transactionHash
        it[block_hash] = receipt.blockHash
        it[block_number] = receipt.blockNumber
        it[gas_used] = receipt.gasUsed
        it[deployer] = receipt.deployer
        it[deploy_index] = receipt.deployIndex
        it[create_time] = receipt.createTime
    }
}