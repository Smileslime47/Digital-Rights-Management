package moe._47saikyo.dao.impl

import domain.User
import moe._47saikyo.dao.UserDao
import moe._47saikyo.models.Users
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
        transaction { Users.select(where).map(Users::resultRowToUser).singleOrNull() }

    override suspend fun getAllUsers(): List<User> =
        transaction { Users.selectAll().map(Users::resultRowToUser) }

    override suspend fun insertUser(user: User): User? =
        transaction {
            Users.insert(Users.getStatementBinder(user)).resultedValues?.singleOrNull()?.let(Users::resultRowToUser)
        }

    override suspend fun updateUser(user: User): Boolean =
        transaction { Users.update({ Users.id eq user.id }, null, Users.getStatementBinder(user)) > 0 }

    override suspend fun deleteUser(user: User): Boolean =
        transaction { Users.deleteWhere { id eq user.id } > 0 }
}