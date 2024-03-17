package moe._47saikyo.configuration.koin

import moe._47saikyo.configuration.security.BCryptPasswordEncoder
import moe._47saikyo.configuration.security.PasswordEncoder
import moe._47saikyo.dao.GroupDao
import moe._47saikyo.dao.UserDao
import moe._47saikyo.dao.impl.GroupDaoImpl
import moe._47saikyo.dao.impl.UserDaoImpl
import moe._47saikyo.service.GroupService
import moe._47saikyo.service.UserService
import moe._47saikyo.service.impl.GroupServiceImpl
import moe._47saikyo.service.impl.UserServiceImpl
import org.koin.dsl.module

/**
 * 依赖注入配置
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
class KoinBackendConfiguration {
    companion object {
        val module = module {
            single<UserDao> { UserDaoImpl() }
            single<UserService> { UserServiceImpl() }
            single<GroupDao> { GroupDaoImpl() }
            single<GroupService> { GroupServiceImpl() }
            single<PasswordEncoder> { BCryptPasswordEncoder() }
        }
    }
}