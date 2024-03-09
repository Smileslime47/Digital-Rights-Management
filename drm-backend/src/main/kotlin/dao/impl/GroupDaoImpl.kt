package moe._47saikyo.dao.impl

import domain.Group
import moe._47saikyo.dao.GroupDao
import moe._47saikyo.models.Groups
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
        transaction { Groups.select(where).map(Groups::resultRowToGroup).singleOrNull() }

    override suspend fun getAllGroups(): List<Group> =
        transaction { Groups.selectAll().map(Groups::resultRowToGroup) }

    override suspend fun insertGroup(group: Group): Group? =
        transaction {
            Groups.insert(Groups.getStatementBinder(group)).resultedValues?.singleOrNull()
                ?.let(Groups::resultRowToGroup)
        }

    override suspend fun updateGroup(group: Group): Boolean =
        transaction { Groups.update({ Groups.id eq group.id }, null, Groups.getStatementBinder(group)) > 0 }

    override suspend fun deleteGroup(group: Group): Boolean =
        transaction { Groups.deleteWhere { id eq group.id } > 0 }
}