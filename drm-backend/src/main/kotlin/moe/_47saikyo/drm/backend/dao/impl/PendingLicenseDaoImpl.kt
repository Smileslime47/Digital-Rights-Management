package moe._47saikyo.drm.backend.dao.impl

import moe._47saikyo.drm.backend.dao.PendingLicenseDao
import moe._47saikyo.drm.backend.mapper.PendingLicenseTable
import moe._47saikyo.drm.core.domain.PendingLicense
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * 待审核授权Dao实现
 *
 * @author 刘一邦
 */
class PendingLicenseDaoImpl : PendingLicenseDao {
    override suspend fun getPendingLicense(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingLicense? =
        transaction {
            PendingLicenseTable
                .select(where)
                .sortedByDescending { it[PendingLicenseTable.create_time] }
                .map(PendingLicenseTable::resultRowToPendingLicense)
                .singleOrNull()
        }

    override suspend fun getPendingLicenses(): List<PendingLicense> =
        transaction {
            PendingLicenseTable
                .selectAll()
                .sortedByDescending { it[PendingLicenseTable.create_time] }
                .map(PendingLicenseTable::resultRowToPendingLicense)
        }

    override suspend fun getPendingLicenses(where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingLicense> =
        transaction {
            PendingLicenseTable
                .select(where)
                .sortedByDescending { it[PendingLicenseTable.create_time] }
                .map(PendingLicenseTable::resultRowToPendingLicense)
        }

    override suspend fun countPendingLicenses(where: SqlExpressionBuilder.() -> Op<Boolean>): Long =
        transaction {
            PendingLicenseTable
                .select(where)
                .count()
        }

    override suspend fun getPendingLicenses(pageSize: Int, pageNumber: Int, where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingLicense> =
        transaction {
            PendingLicenseTable
                .select(where)
                .limit(pageSize, (pageNumber - 1) * pageSize.toLong())
                .sortedByDescending { it[PendingLicenseTable.create_time] }
                .map(PendingLicenseTable::resultRowToPendingLicense)
        }

    override suspend fun insertPendingLicense(pendingLicense: PendingLicense): PendingLicense? =
        transaction {
            try {
                PendingLicenseTable
                    .insert(PendingLicenseTable.getStatementBinder(pendingLicense))
                    .resultedValues?.singleOrNull()?.let(PendingLicenseTable::resultRowToPendingLicense)
            } catch (e: ExposedSQLException) {
                null
            }
        }

    override suspend fun updatePendingLicense(pendingLicense: PendingLicense): Boolean =
        transaction {
            PendingLicenseTable
                .update({ PendingLicenseTable.id eq pendingLicense.id }, null, PendingLicenseTable.getStatementBinder(pendingLicense)) > 0
        }

    override suspend fun deletePendingLicense(pendingLicense: PendingLicense): Boolean =
        transaction {
            PendingLicenseTable
                .deleteWhere { PendingLicenseTable.id eq pendingLicense.id } > 0
        }
}