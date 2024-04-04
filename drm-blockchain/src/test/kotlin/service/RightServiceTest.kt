package service

import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import moe._47saikyo.service.impl.RightServiceImpl
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
        BlockChain.connect(
            BlockChainConfigurationBuilder()
                .withChain("http://192.168.10.108:8545", 721)
                .withBankWallet(
                    "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                    "1234567890"
                )
                .withManager("0xb65881ac2417778c8495128892d900e1a9fd19d1")
                .build()
        )
    }

    /**
     * 测试获取指定owner版权列表
     */
    @Test
    fun getRightsTest() {
        logger.info(RightServiceImpl().getRights("0x31e582af2d0baeaa563a908eca1af273caeb7c0e").toString())
    }
}