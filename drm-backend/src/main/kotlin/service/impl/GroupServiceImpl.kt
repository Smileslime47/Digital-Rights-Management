package moe._47saikyo.service.impl

import moe._47saikyo.dao.GroupDao
import moe._47saikyo.domain.Group
import moe._47saikyo.mapper.GroupTable
import moe._47saikyo.service.GroupService
import org.koin.java.KoinJavaComponent

/**
 * GroupService实现
 *
 * @author 刘一邦
 */
class GroupServiceImpl : GroupService {
    private val groupDao: GroupDao by KoinJavaComponent.inject(GroupDao::class.java)
    override suspend fun getGroup(id: Long): Group? =
        groupDao.getGroup { GroupTable.id eq id }

    override suspend fun authenticate(groupId: Long, condition: (Group) -> Boolean): Boolean {
        val group = getGroup(groupId)
        return if (group == null) false else condition(group)
    }
}