import moe._47saikyo.*
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.Right
import org.slf4j.LoggerFactory
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * EstimateTest
 *
 * @author 刘一邦
 */
class EstimateTest {
    private val logger = LoggerFactory.getLogger(EstimateTest::class.java)

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
     * 测试部署合约的估算
     */
    @Test
    fun testEstimateDeploy() {
        val binCode = Right.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string("title"),
                string("registrationNumber"),
                uint64(111),
                uint64(111),
                string("description")
            )
        )

        logger.info(
            (Estimate.estimateDeploy("$binCode$encodedConstructor").toLong() * BlockChain.gasProvider.gasPrice.toLong()).toString()
        )
    }

    /**
     * 测试调用合约的估算
     */
    @Test
    fun testEstimateCall() {
        logger.info(
            (Estimate.estimateCall(
                "0xfe5078c71a0fc05ab17429472fb2b310612706e5",
                "addRight",
                listOf(
                    address(BlockChainConstant.ADDRESS_PLACEHOLDER)
                ),
                listOf(
                    TypeReference.makeTypeReference(BlockChainConstant.SolidityType.ADDRESS)
                )
            ).toLong() * BlockChain.gasProvider.gasPrice.toLong()).toString()
        )
    }
}