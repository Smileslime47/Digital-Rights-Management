package moe._47saikyo.drm.backend.dao.impl

import moe._47saikyo.drm.backend.dao.PendingRightDao
import moe._47saikyo.drm.backend.mapper.PendingRightTable
import moe._47saikyo.drm.core.domain.PendingRight
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * 待审核版权Dao实现
 *
 * @author 刘一邦
 */
class PendingRightDaoImpl : PendingRightDao {
    override suspend fun getPendingRight(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingRight? =
        transaction {
            PendingRightTable
                .select(where)
                .sortedByDescending { it[PendingRightTable.create_time] }
                .map(PendingRightTable::resultRowToPendingRight)
                .singleOrNull()
        }

    override suspend fun getPendingRights(): List<PendingRight> =
        transaction {
            PendingRightTable
                .selectAll()
                .sortedByDescending { it[PendingRightTable.create_time] }
                .map(PendingRightTable::resultRowToPendingRight)
        }

    override suspend fun getPendingRights(where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingRight> =
        transaction {
            PendingRightTable
                .select(where)
                .sortedByDescending { it[PendingRightTable.create_time] }
                .map(PendingRightTable::resultRowToPendingRight)
        }

    override suspend fun countPendingRights(where: SqlExpressionBuilder.() -> Op<Boolean>): Long =
        transaction {
            PendingRightTable
                .select(where)
                .count()
        }

    override suspend fun getPendingRights(pageSize: Int, pageNumber: Int, where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingRight> =
        transaction {
            PendingRightTable
                .select(where)
                .limit(pageSize, (pageNumber - 1) * pageSize.toLong())
                .sortedByDescending { it[PendingRightTable.create_time] }
                .map(PendingRightTable::resultRowToPendingRight)
        }

    override suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight? =
        transaction {
            PendingRightTable
                .insert(PendingRightTable.getStatementBinder(pendingRight))
                .resultedValues?.singleOrNull()?.let(PendingRightTable::resultRowToPendingRight)
        }

    override suspend fun updatePendingRight(pendingRight: PendingRight): Boolean =
        transaction {
            PendingRightTable
                .update({ PendingRightTable.id eq pendingRight.id }, null, PendingRightTable.getStatementBinder(pendingRight)) > 0
        }

    override suspend fun deletePendingRight(pendingRight: PendingRight): Boolean =
        transaction {
            PendingRightTable
                .deleteWhere { id eq pendingRight.id } > 0
        }
}