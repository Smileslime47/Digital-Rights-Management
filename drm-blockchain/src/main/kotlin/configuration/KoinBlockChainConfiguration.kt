package moe._47saikyo.configuration

import moe._47saikyo.service.AccountService
import moe._47saikyo.service.impl.AccountServiceImpl
import org.koin.dsl.module

class KoinBlockChainConfiguration {
    companion object {
        val module = module {
            single<AccountService> { AccountServiceImpl() }
        }
    }
}