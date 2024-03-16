package moe._47saikyo.blockchain

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * BlockDao接口
 *
 * @author 刘一邦
 */
object BlockDao {
    fun getBlock(where: SqlExpressionBuilder.() -> Op<Boolean>): Block? =
        transaction { BlockTable.select(where).map(BlockTable::resultRowToBlock).singleOrNull() }

    fun getAllBlocks(): List<Block> =
        transaction { BlockTable.selectAll().map(BlockTable::resultRowToBlock) }

    fun insertBlock(block: Block): Block? =
        transaction {
            BlockTable.insert(BlockTable.getStatementBinder(block)).resultedValues?.singleOrNull()
                ?.let(BlockTable::resultRowToBlock)
        }
}