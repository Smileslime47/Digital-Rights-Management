package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainTest
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent
import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test

class LicenseServiceTest {
    private val logger = LoggerFactory.getLogger(LicenseServiceTest::class.java)
    private val accountService: AccountService by KoinJavaComponent.inject(AccountService::class.java)
    private val licenseService: LicenseService by KoinJavaComponent.inject(LicenseService::class.java)

    /**
     * 初始化区块链连接
     */
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(KoinBlockChainWrapperConfiguration.module)
        }

        BlockChainTest.init()
    }
}