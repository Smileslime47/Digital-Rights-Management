package service

import moe._47saikyo.BlockChain
import moe._47saikyo.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.contract.License
import moe._47saikyo.service.AccountService
import moe._47saikyo.service.LicenseService
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
            modules(KoinBlockChainConfiguration.module)
        }

        BlockChainTest.init()
    }

    @Test
    fun getTest() {
        val license = License.load(
            "0xb128cd53adf278888f72638fbb6ac1844af2ff0e",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )
    }

    @Test
    fun serializeTest() {
        val license = License.load(
            "0xb128cd53adf278888f72638fbb6ac1844af2ff0e",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )
        val json = license.serialize().send()
        logger.info("getPureData[$json]")
    }

    @Test
    fun getRightsTest() {
        logger.info(licenseService.getLicenses("0x31e582af2d0baeaa563a908eca1af273caeb7c0e").toString())
    }
}