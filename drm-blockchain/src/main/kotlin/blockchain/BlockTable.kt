package moe._47saikyo.blockchain

import constant.GlobalConstant
import domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * 表映射类
 * CREATE TABLE `drm_blockchain` (
 *   `hash` varchar(128) NOT NULL,
 *   `previous_hash` varchar(128) DEFAULT NULL,
 *   `id` bigint(20) NOT NULL,
 *   `timestamp` bigint(20) NOT NULL,
 *   `data` blob DEFAULT NULL
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
 *
 * @author 刘一邦
 */
object BlockTable : Table("drm_blockchain") {
    val hash = varchar("hash", 128)
    val previous_hash = varchar("previous_hash", 128)
    val index = long("index")
    val timestamp = long("id")
    val data = text("data")
    override val primaryKey = PrimaryKey(hash)

    fun resultRowToBlock(row: ResultRow) = Block(
        index = row[index],
        hash = row[hash],
        previousHash = row[previous_hash],
        timestamp = row[timestamp],
        data = row[data]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(block: Block): BlockTable.(statement: T) -> Unit = {
        it[index] = block.index
        it[hash] = block.hash
        it[previous_hash] = block.previousHash ?: GlobalConstant.NULL_PLACEHOLDER
        it[timestamp] = block.timestamp
        it[data] = block.data ?: GlobalConstant.NULL_PLACEHOLDER
    }
}