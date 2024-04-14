package service

import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import moe._47saikyo.Estimate
import moe._47saikyo.address
import moe._47saikyo.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.service.ManagerService
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test

class ManagerServiceTest {
    private val logger = LoggerFactory.getLogger(ManagerServiceTest::class.java)
    private val managerService: ManagerService by inject(ManagerService::class.java)

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
                .withManager("0xef563dee888cb304dd660b9f6e6b261f1a2295d2")
                .build()
        )
    }

    @Test
    fun testAddGas() {
        assert(
            Estimate.estimateCall(
                "0xb65881ac2417778c8495128892d900e1a9fd19d1",
                "addRight",
                listOf(
                    address(BlockChainConstant.ADDRESS_PLACEHOLDER)
                ),
                emptyList()
            ) == BlockChainConstant.Gas.MANAGER_ADD//21432L
        )
    }
}