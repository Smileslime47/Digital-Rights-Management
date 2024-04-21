package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.GroupDao
import moe._47saikyo.drm.backend.mapper.GroupTable
import moe._47saikyo.drm.backend.service.GroupService
import moe._47saikyo.drm.core.domain.Group
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