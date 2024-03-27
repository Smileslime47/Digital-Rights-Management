package moe._47saikyo.dao

import domain.PendingRight
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

interface PendingRightDao {
    suspend fun getPendingRight(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingRight?
    suspend fun getPendingRights(): List<PendingRight>
    suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight?
    suspend fun updatePendingRight(pendingRight: PendingRight): Boolean
    suspend fun deletePendingRight(pendingRight: PendingRight): Boolean
}