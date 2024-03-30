package moe._47saikyo.service

import domain.Group

/**
 * GroupService接口
 *
 * @author 刘一邦
 * @since 2024/03/07
 */
interface GroupService {
    /**
     * 获取用户组
     *
     * @param id 用户组ID
     * @return 用户组
     */
    suspend fun getGroup(id:Long): Group?

    /**
     * 验证用户组权限
     *
     * @param groupId 用户组ID
     * @param condition 用户组权限
     * @return 验证结果
     */
    suspend fun authenticate(groupId: Long, condition: (Group) -> Boolean): Boolean
}