package moe._47saikyo.configuration.koin

import moe._47saikyo.service.AccountService
import moe._47saikyo.service.LicenseService
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import moe._47saikyo.service.impl.AccountServiceImpl
import moe._47saikyo.service.impl.LicenseEthCallService
import moe._47saikyo.service.impl.ManagerEthCallService
import moe._47saikyo.service.impl.RightEthCallService
import org.koin.dsl.module

/**
 * Koin配置类
 *
 * @author 刘一邦
 */
object KoinBlockChainConfiguration {
    val module = module {
        single<AccountService> { AccountServiceImpl() }
        single<RightService> { RightEthCallService() }
        single<LicenseService> { LicenseEthCallService() }
        single<ManagerService> { ManagerEthCallService() }
    }
}