package service

import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import moe._47saikyo.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.service.AccountService
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test

class AccountServiceTest {
    val accountService: AccountService by inject(AccountService::class.java)
    val logger = LoggerFactory.getLogger(AccountServiceTest::class.java)

    /**
     * 初始化区块链连接
     */
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(KoinBlockChainConfiguration.module)
        }

        BlockChain.connect(
            BlockChainConfigurationBuilder()
                .withChain("http://localhost:8545", 721)
                .withBankWallet(
                    "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                    "1234567890"
                )
                .withManager("0xef563dee888cb304dd660b9f6e6b261f1a2295d2")
                .build()
        )
    }

    @Test
    fun getBalanceTest() {
        logger.info(accountService.getBalance("0xcb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe").toString())
        logger.info(BlockChain.web3jInstance!!.ethChainId().send().chainId.toString())
    }
}