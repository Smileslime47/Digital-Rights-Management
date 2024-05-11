package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainTest
import moe._47saikyo.drm.blockchain.Estimate
import moe._47saikyo.drm.blockchain.address
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.blockchain.constant.BlockChainConstant
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.utils.Convert
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ManagerServiceTest {
    private val logger = LoggerFactory.getLogger(ManagerServiceTest::class.java)
    private val managerService: ManagerService by inject(ManagerService::class.java)

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
    fun testSearchRights() {
        val rights = managerService.searchByTitle("0x4db3443fc610477a5260c350254c9003b63e0673", "Te").toString()
        logger.info("rights: $rights")
    }

    @Test
    fun pingTest() {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        assertEquals("pong", manager.ping().send())
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