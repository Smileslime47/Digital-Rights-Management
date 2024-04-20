import com.fasterxml.jackson.databind.ObjectMapper
import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import moe._47saikyo.configuration.koin.KoinBlockChainConfiguration
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.models.LicenseDeployForm
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.AccountService
import moe._47saikyo.service.impl.AccountServiceImpl
import moe._47saikyo.service.impl.LicenseServiceImpl
import moe._47saikyo.service.impl.RightServiceImpl
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

    /**
     * 初始化区块链连接
     */
    @BeforeTest
    fun setup() {
        startKoin {
            modules(KoinBlockChainConfiguration.module)
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
     * 上一次部署成功地址：0xef563dee888cb304dd660b9f6e6b261f1a2295d2
     */
    @Test
    fun deployManager() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val manager = DRManager.deploy(BlockChain.web3jInstance, BlockChain.bankTxManager, BlockChain.gasProvider).send()

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Manager deployed at ${manager.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
    }
    /**
     * 部署测试用版权合约
     *
     * 上一次部署成功地址：0x5e10e07433ae5419c798902b219b76f5cd6eef8d
     */
    @Test
    fun deployRight() {
        val form = RightDeployForm(
            "TestRight",
            "testOwner",
            "0721",
            BigInteger.valueOf(System.currentTimeMillis()),
            BigInteger.valueOf(System.currentTimeMillis() + 360000L),
            "TestRightDescription",
            "TestRightFileName",
            "TestRightFileHash"
        )

        val estimateCost = RightServiceImpl().estimateDeploy(BlockChain.bankAddress!!,form).toLong() * BlockChain.gasProvider.gasPrice.toLong()

        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val right = RightServiceImpl().addRight(
            BlockChain.bankTxManager!!,
            form
        )

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Right deployed at ${right.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
        logger.info("estimate cost $estimateCost wei.")
    }

    /**
     * 部署测试用授权合约
     *
     * 上一次部署成功地址：0x9c2cb0dc39e31484991efc461a9feab8ce0baf61
     */
    @Test
    fun deployLicense(){
        val form = LicenseDeployForm(
             "0xc190cb0662ae5a22f944557b2c0063cbcc6091ee",
            BigInteger.valueOf(System.currentTimeMillis()),
            BigInteger.valueOf(System.currentTimeMillis() + 360000L),
        )

        val estimateCost = LicenseServiceImpl().estimateDeploy(form).toLong() * BlockChain.gasProvider.gasPrice.toLong()

        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val license = License.deploy(
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider,
            form.right,
            form.issueTime,
            form.expireTime
        ).send()

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Right deployed at ${license.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
        logger.info("estimate cost $estimateCost wei.")
    }
}