package moe._47saikyo.dao

import domain.User
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

/**
 * UserDao接口
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
interface UserDao {
    suspend fun getUser(where: SqlExpressionBuilder.() -> Op<Boolean>): User?
    suspend fun getAllUsers(): List<User>
    suspend fun insertUser(user:User):User?
    suspend fun updateUser(user:User):Boolean
    suspend fun deleteUser(user:User):Boolean
}