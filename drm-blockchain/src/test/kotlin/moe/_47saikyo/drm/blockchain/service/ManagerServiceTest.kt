package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainTest
import moe._47saikyo.drm.blockchain.Estimate
import moe._47saikyo.drm.blockchain.address
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.drm.blockchain.constant.BlockChainConstant
import moe._47saikyo.drm.blockchain.contract.License
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.utils.Convert
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
    fun testAddLicense() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!, Convert.Unit.WEI)

        val license = License.load(
            "0xb128cd53adf278888f72638fbb6ac1844af2ff0e",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )
        managerService.addLicense(BlockChain.bankTxManager!!,license)

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)
        logger.info("cost ${oldBalance - newBalance} wei.")
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