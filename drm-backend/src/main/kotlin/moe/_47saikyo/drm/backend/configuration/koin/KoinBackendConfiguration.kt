package moe._47saikyo.drm.backend.configuration.koin

import moe._47saikyo.drm.backend.configuration.security.BCryptPasswordEncoder
import moe._47saikyo.drm.backend.configuration.security.PasswordEncoder
import moe._47saikyo.drm.backend.dao.*
import moe._47saikyo.drm.backend.dao.impl.*
import moe._47saikyo.drm.backend.service.*
import moe._47saikyo.drm.backend.service.impl.*
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
            single<PendingLicenseDao> { PendingLicenseDaoImpl() }
            single<PendingLicenseService> { PendingLicenseServiceImpl() }
            single<NoticeDao> { NoticeDaoImpl() }
            single<NoticeService> { NoticeServiceImpl() }
            single<WalletDao> { WalletDaoImpl() }
            single<WalletService> { WalletServiceImpl() }
            single<PasswordEncoder> { BCryptPasswordEncoder() }
        }
    }
}