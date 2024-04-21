package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.GroupDao
import moe._47saikyo.drm.backend.dao.UserDao
import moe._47saikyo.drm.backend.mapper.GroupTable
import moe._47saikyo.drm.backend.mapper.UserTable
import moe._47saikyo.drm.backend.service.UserService
import moe._47saikyo.drm.core.domain.Group
import moe._47saikyo.drm.core.domain.User
import org.koin.java.KoinJavaComponent.inject

/**
 * UserService实现
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
class UserServiceImpl : UserService {
    private val userDao: UserDao by inject(UserDao::class.java)
    private val groupDao: GroupDao by inject(GroupDao::class.java)

    override suspend fun getDebugUser(): User =
        userDao.getUser { UserTable.username eq "DebugUser" } ?: error("找不到用户")

    override suspend fun getUser(id: Long): User? =
        userDao.getUser { UserTable.id eq id }

    override suspend fun getUser(username: String): User? =
        userDao.getUser { UserTable.username eq username }

    override suspend fun getUserGroup(userid: Long): Group? {
        val user = getUser(userid)
        return if (user == null) null else groupDao.getGroup { GroupTable.id eq user.permissionId }
    }

    override suspend fun insertUser(user: User): User? =
        userDao.insertUser(user)

    override suspend fun updateUser(user: User): Boolean =
        userDao.updateUser(user)
}