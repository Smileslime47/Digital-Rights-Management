package moe._47saikyo.configuration.koin

import moe._47saikyo.service.AccountService
import moe._47saikyo.service.LicenseService
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import moe._47saikyo.service.impl.*
import org.koin.dsl.module

/**
 * Koin配置类
 *
 * @author 刘一邦
 */
object KoinBlockChainWrapperConfiguration {
    val module = module {
        single<AccountService> { AccountServiceImpl() }
        single<RightService> { RightWrapperService() }
        single<LicenseService> { LicenseWrapperService() }
        single<ManagerService> { ManagerWrapperService() }
    }
}