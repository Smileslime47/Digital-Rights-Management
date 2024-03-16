package moe._47saikyo.service

import domain.Group
import domain.User

/**
 * UserService接口
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
interface UserService {
    suspend fun getDebugUser(): User
    suspend fun getUser(id: Long): User?
    suspend fun getUser(username: String): User?
    suspend fun getUserGroup(userid: Long): Group?
    suspend fun insertUser(user: User): User?
    suspend fun updateUser(user: User): Boolean
}