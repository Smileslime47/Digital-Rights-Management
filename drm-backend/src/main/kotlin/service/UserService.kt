package moe._47saikyo.service

import moe._47saikyo.domain.Group
import moe._47saikyo.domain.User

/**
 * UserService接口
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
interface UserService {
    /**
     * 获取Debug用户
     *
     * @return Debug用户
     */
    suspend fun getDebugUser(): User

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return 用户
     */
    suspend fun getUser(id: Long): User?

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    suspend fun getUser(username: String): User?

    /**
     * 获取用户组
     *
     * @param userid 用户ID
     * @return 用户组
     */
    suspend fun getUserGroup(userid: Long): Group?

    /**
     * 插入用户
     *
     * @param user 用户
     * @return 用户
     */
    suspend fun insertUser(user: User): User?

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 是否成功
     */
    suspend fun updateUser(user: User): Boolean
}