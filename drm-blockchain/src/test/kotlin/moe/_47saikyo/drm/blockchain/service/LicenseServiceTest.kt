package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainTest
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent
import org.slf4j.LoggerFactory
import java.math.BigInteger
import kotlin.test.BeforeTest
import kotlin.test.Test

class LicenseServiceTest {
    private val logger = LoggerFactory.getLogger(LicenseServiceTest::class.java)
    private val accountService: AccountService by KoinJavaComponent.inject(AccountService::class.java)
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)
    private val licenseService: LicenseService by KoinJavaComponent.inject(LicenseService::class.java)

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
    fun getTest() {
        val licenses = licenseService.getLicenses("0xb975997898b583d2e3095ab5aa4b7e218a7f3c51", BlockChain.bankAddress!!)
        val license = managerService.getLicense("0xb975997898b583d2e3095ab5aa4b7e218a7f3c51",  BlockChain.bankAddress!!, 0)
        for (license in licenses) {
            logger.info(license.toString())
        }
    }

    @Test
    fun addTest() {
        managerService.addLicense(
            BlockChain.bankTxManager!!,
            DRManager.License(
                BigInteger.valueOf(0),
                "Arcaea_AI_Chan",
                DRManager.KeyPair("0x782fad4153d2bd2ebcab81df12862ce4aae4fe32", BigInteger.valueOf(0)),
                BlockChain.bankAddress,
                "User",
                BigInteger.valueOf(1715184000000),
                BigInteger.valueOf(1718640000000),
                ""
            )
        )
//        licenseService.addLicense(
//            BlockChain.bankTxManager!!,
//            LicenseDeployForm(
//                "Arcaea_AI_Chan",
//                KeyPairData("0x782fad4153d2bd2ebcab81df12862ce4aae4fe32", 0),
//                "User",
//                BigInteger.valueOf(1715184000000),
//                BigInteger.valueOf(1718640000000),
//                "",
//            )
//        )
    }
}