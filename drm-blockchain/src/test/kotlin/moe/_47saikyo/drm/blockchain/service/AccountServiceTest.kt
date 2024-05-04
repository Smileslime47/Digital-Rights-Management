package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainTest
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
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
            modules(KoinBlockChainWrapperConfiguration.module)
        }

        BlockChainTest.init()
    }

    @Test
    fun getBalanceTest() {
        logger.info(accountService.getBalance("0x4db3443fc610477a5260c350254c9003b63e0673").toString())
        logger.info(BlockChain.web3jInstance!!.ethChainId().send().chainId.toString())
    }
}