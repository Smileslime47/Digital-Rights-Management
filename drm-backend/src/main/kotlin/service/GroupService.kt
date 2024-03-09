package moe._47saikyo.service

import domain.Group

/**
 * GroupService接口
 *
 * @author 刘一邦
 * @since 2024/03/07
 */
interface GroupService {
    suspend fun getGroup(id:Long): Group?

    suspend fun authenticate(groupId: Long, condition: (Group) -> Boolean): Boolean
}