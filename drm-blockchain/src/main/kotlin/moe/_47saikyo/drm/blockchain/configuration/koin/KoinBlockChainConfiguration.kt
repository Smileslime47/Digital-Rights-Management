package moe._47saikyo.drm.blockchain.configuration.koin

import moe._47saikyo.drm.blockchain.service.AccountService
import moe._47saikyo.drm.blockchain.service.LicenseService
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.RightService
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import moe._47saikyo.drm.blockchain.service.impl.LicenseEthCallService
import moe._47saikyo.drm.blockchain.service.impl.ManagerEthCallService
import moe._47saikyo.drm.blockchain.service.impl.RightEthCallService
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