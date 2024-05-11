package moe._47saikyo.drm.blockchain.service

import com.fasterxml.jackson.databind.ObjectMapper
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
        logger.info("NetPeers:${BlockChain.web3jInstance!!.netPeerCount().send().quantity}")
        BlockChain.web3jInstance!!.adminPeers().send().result.forEach {
            logger.info("Peer:")
            logger.info("  Id:${it.id}")
            logger.info("  Name:${it.name}")
            logger.info("  Enode:${it.enode}")
            logger.info("  Network:${it.network}")
        }
    }

    @Test
    fun newAccountTest() {
        val pwd = "1234567890"
        logger.info(ObjectMapper().writeValueAsString(accountService.newAccount(pwd)))
    }

    @Test
    fun getBalanceTest() {
        logger.info(accountService.getBalance(BlockChain.bankAddress!!).toString())
        logger.info(BlockChain.web3jInstance!!.ethChainId().send().chainId.toString())
    }
}