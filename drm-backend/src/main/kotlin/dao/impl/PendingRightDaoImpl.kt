package moe._47saikyo.dao.impl

import moe._47saikyo.dao.PendingRightDao
import moe._47saikyo.mapper.PendingRightTable
import domain.PendingRight
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * 待审核版权Dao实现
 *
 * @author 刘一邦
 */
class PendingRightDaoImpl :PendingRightDao{
    override suspend fun getPendingRight(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingRight? =
        transaction { PendingRightTable.select(where).map(PendingRightTable::resultRowToPendingRight).singleOrNull() }

    override suspend fun getPendingRights(): List<PendingRight> =
        transaction { PendingRightTable.selectAll().map(PendingRightTable::resultRowToPendingRight) }

    override suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight? =
        transaction {
            PendingRightTable.insert(PendingRightTable.getStatementBinder(pendingRight)).resultedValues?.singleOrNull()?.let(PendingRightTable::resultRowToPendingRight)
        }

    override suspend fun updatePendingRight(pendingRight: PendingRight): Boolean =
        transaction { PendingRightTable.update({ PendingRightTable.id eq pendingRight.id }, null, PendingRightTable.getStatementBinder(pendingRight)) > 0 }

    override suspend fun deletePendingRight(pendingRight: PendingRight): Boolean =
        transaction { PendingRightTable.deleteWhere { id eq pendingRight.id } > 0 }
}