package moe._47saikyo.drm.backend.dao.impl

import moe._47saikyo.drm.backend.dao.GroupDao
import moe._47saikyo.drm.backend.mapper.GroupTable
import moe._47saikyo.drm.core.domain.Group
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * GroupDao实现
 *
 * @author 刘一邦
 * @since 2024/03/07
 */
class GroupDaoImpl : GroupDao {
    override suspend fun getGroup(where: SqlExpressionBuilder.() -> Op<Boolean>): Group? =
        transaction { GroupTable.select(where).map(GroupTable::resultRowToGroup).singleOrNull() }

    override suspend fun getGroups(): List<Group> =
        transaction { GroupTable.selectAll().map(GroupTable::resultRowToGroup) }

    override suspend fun insertGroup(group: Group): Group? =
        transaction {
            try {
                GroupTable.insert(GroupTable.getStatementBinder(group)).resultedValues?.singleOrNull()
                    ?.let(GroupTable::resultRowToGroup)
            } catch (e: ExposedSQLException) {
                null
            }
        }

    override suspend fun updateGroup(group: Group): Boolean =
        transaction { GroupTable.update({ GroupTable.id eq group.id }, null, GroupTable.getStatementBinder(group)) > 0 }

    override suspend fun deleteGroup(group: Group): Boolean =
        transaction { GroupTable.deleteWhere { id eq group.id } > 0 }
}