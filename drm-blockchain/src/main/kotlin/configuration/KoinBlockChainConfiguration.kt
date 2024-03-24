package moe._47saikyo.configuration

import moe._47saikyo.service.AccountService
import moe._47saikyo.service.RightService
import moe._47saikyo.service.impl.AccountServiceImpl
import moe._47saikyo.service.impl.RightServiceImpl
import org.koin.dsl.module

/**
 * Koin配置类
 *
 * @author 刘一邦
 */
class KoinBlockChainConfiguration {
    companion object {
        val module = module {
            single<AccountService> { AccountServiceImpl() }
            single<RightService> { RightServiceImpl() }
        }
    }
}