package service

import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import moe._47saikyo.Estimate
import moe._47saikyo.address
import moe._47saikyo.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.service.impl.RightServiceImpl
import org.koin.core.context.startKoin
import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * RightServiceTest
 *
 * @author 刘一邦
 */
class RightServiceTest {
    private val logger = LoggerFactory.getLogger(RightServiceTest::class.java)

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
                .withChain("http://192.168.10.108:8545", 721)
                .withBankWallet(
                    "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                    "1234567890"
                )
                .withManager("0x9c2cb0dc39e31484991efc461a9feab8ce0baf61")
                .build()
        )
    }

    /**
     * 测试获取指定owner版权列表
     */
    @Test
    fun getRightsTest() {
        logger.info(RightServiceImpl().getPureData("0x5e10e07433ae5419c798902b219b76f5cd6eef8d").toString())
        logger.info(RightServiceImpl().getRights("0xcb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe").toString())
    }

    @Test
    fun testAddGas(){
        assert(
            Estimate.estimateCall(
                "0xc190cb0662ae5a22f944557b2c0063cbcc6091ee",
                "addLicense",
                listOf(
                    address(BlockChainConstant.ADDRESS_PLACEHOLDER)
                ),
                emptyList()
            ) == BlockChainConstant.Gas.RIGHT_ADD//66014L
        )
    }
}