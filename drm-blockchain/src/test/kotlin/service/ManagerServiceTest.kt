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

        BlockChainTest.init()
    }

    @Test
    fun testSearchRights() {
        val rights = managerService.searchByTitle("0x4db3443fc610477a5260c350254c9003b63e0673","Te").toString()
        logger.info("rights: $rights")
    }

    @Test
    fun testGetRights() {
        val rights = managerService.getRights("0x31e582af2d0baeaa563a908eca1af273caeb7c0e")
        logger.info("rights: $rights")
    }

    @Test
    fun testAddGas() {
        assert(
            Estimate.estimateCall(
                "0x4db3443fc610477a5260c350254c9003b63e0673",
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