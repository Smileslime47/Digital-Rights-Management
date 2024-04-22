package moe._47saikyo.drm.blockchain

import com.fasterxml.jackson.databind.ObjectMapper
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import moe._47saikyo.drm.blockchain.service.AccountService
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.utils.Convert
import java.math.BigInteger
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * 预部署测试用合约
 *
 * @author 刘一邦
 */
class PreDeploy {
    private val logger = LoggerFactory.getLogger(PreDeploy::class.java)
    private val accountService: AccountService by inject(AccountService::class.java)
    private val managerService: ManagerService by inject(ManagerService::class.java)

    /**
     * 初始化区块链连接
     */
    @BeforeTest
    fun setup() {
        startKoin {
            modules(KoinBlockChainWrapperConfiguration.module)
        }

        BlockChainTest.init()
    }

    @Test
    fun newAccount() {
        logger.info(ObjectMapper().writeValueAsString(accountService.newAccount("1234567890")))
    }

    /**
     * 预部署测试用DRManager合约
     *
     * 上一次部署成功地址：0x13598c1e0e73d0793a29e35e8831aad41e40bfdc
     */
    @Test
    fun deployManager() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val manager = DRManager.deploy(BlockChain.web3jInstance, BlockChain.bankTxManager, BlockChain.gasProvider).send()

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Manager deployed at ${manager.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
    }
}