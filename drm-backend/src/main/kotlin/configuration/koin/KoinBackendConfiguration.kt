package moe._47saikyo.configuration.koin

import moe._47saikyo.configuration.security.BCryptPasswordEncoder
import moe._47saikyo.configuration.security.PasswordEncoder
import moe._47saikyo.dao.*
import moe._47saikyo.dao.impl.*
import moe._47saikyo.service.*
import moe._47saikyo.service.impl.*
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
            single<PendingRightDao> { PendingRightDaoImpl() }
            single<PendingRightService> { PendingRightServiceImpl() }
            single<NoticeDao> { NoticeDaoImpl() }
            single<NoticeService> { NoticeServiceImpl() }
            single<PasswordEncoder> { BCryptPasswordEncoder() }
        }
    }
}