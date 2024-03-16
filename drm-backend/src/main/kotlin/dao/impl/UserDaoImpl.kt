package moe._47saikyo.dao.impl

import domain.User
import moe._47saikyo.dao.UserDao
import moe._47saikyo.models.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * UserDao实现
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
class UserDaoImpl : UserDao {
    override suspend fun getUser(where: SqlExpressionBuilder.() -> Op<Boolean>): User? =
        transaction { UserTable.select(where).map(UserTable::resultRowToUser).singleOrNull() }

    override suspend fun getAllUsers(): List<User> =
        transaction { UserTable.selectAll().map(UserTable::resultRowToUser) }

    override suspend fun insertUser(user: User): User? =
        transaction {
            UserTable.insert(UserTable.getStatementBinder(user)).resultedValues?.singleOrNull()?.let(UserTable::resultRowToUser)
        }

    override suspend fun updateUser(user: User): Boolean =
        transaction { UserTable.update({ UserTable.id eq user.id }, null, UserTable.getStatementBinder(user)) > 0 }

    override suspend fun deleteUser(user: User): Boolean =
        transaction { UserTable.deleteWhere { id eq user.id } > 0 }
}