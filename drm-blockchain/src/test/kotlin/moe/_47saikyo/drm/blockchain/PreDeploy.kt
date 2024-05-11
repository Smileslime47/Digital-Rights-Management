package moe._47saikyo.drm.blockchain

import com.fasterxml.jackson.databind.ObjectMapper
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.service.AccountService
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.impl.AccountServiceImpl
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.utils.Convert
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
        val pwd = "1234567890"
        logger.info(ObjectMapper().writeValueAsString(accountService.newAccount(pwd)))
    }

    /**
     * 预部署测试用DRManager合约
     *
     * 上一次部署成功地址：0xbfe6ffcd87d4aa8d28239a1f5afbb22a3dd208f5
     */
    @Test
    fun deployManager() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info(BlockChain.bankAddress!!)
        val manager = DRManager.deploy(BlockChain.web3jInstance, BlockChain.bankTxManager, BlockChain.gasProvider).send()

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Manager deployed at ${manager.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
        logger.info("Left $newBalance wei.")
    }
}