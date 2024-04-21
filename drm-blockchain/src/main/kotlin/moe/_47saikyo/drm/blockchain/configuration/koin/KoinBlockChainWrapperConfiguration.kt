package moe._47saikyo.drm.blockchain.configuration.koin

import moe._47saikyo.drm.blockchain.service.AccountService
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.RightService
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import moe._47saikyo.drm.blockchain.service.impl.LicenseWrapperService
import moe._47saikyo.drm.blockchain.service.impl.ManagerWrapperService
import moe._47saikyo.drm.blockchain.service.impl.RightWrapperService
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