import moe._47saikyo.*
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.service.impl.AccountServiceImpl
import moe._47saikyo.service.impl.RightServiceImpl
import org.slf4j.LoggerFactory
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.utils.Convert
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
     * 测试合约加载开销
     */
    @Test
    fun testRightLoad(){
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!, Convert.Unit.WEI)

        val right = Right.load(
            "0xc190cb0662ae5a22f944557b2c0063cbcc6091ee",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Right loaded at ${right.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
    }

    /**
     * 测试合约加载开销
     */
    @Test
    fun testLicenseLoad(){
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!, Convert.Unit.WEI)

        val license = License.load(
            "0x9c2cb0dc39e31484991efc461a9feab8ce0baf61",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("License loaded at ${license.contractAddress}.")
        logger.info("cost ${oldBalance - newBalance} wei.")
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

    /**
     * 确认View函数的调用不会消耗gas
     */
    @Test
    fun testViewFunctionCall() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val function = Function("serialize", emptyList(), listOf(TypeReference.makeTypeReference("string")))
        val transaction = Transaction.createFunctionCallTransaction(
            BlockChain.bankAddress,
            BlockChain.getNextNonce(BlockChain.bankAddress!!),
            BlockChain.gasProvider.gasPrice,
            BlockChain.gasProvider.gasLimit,
            "0xc190cb0662ae5a22f944557b2c0063cbcc6091ee",
            FunctionEncoder.encode(function)
        )
        val estimateCost =  BlockChain.web3jInstance!!.ethEstimateGas(transaction).send().amountUsed.toLong() * BlockChain.gasProvider.gasPrice.toLong()

        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).send()
        val outputs = FunctionReturnDecoder.decode(result.value, function.outputParameters)

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Call Result:${outputs[0].value as String}")
        logger.info("cost ${oldBalance - newBalance} wei.")
        logger.info("estimate cost $estimateCost wei.")
    }

    /**
     * 确认View函数的调用不会消耗gas
     */
    @Test
    fun testContractCall() {
        val oldBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        val right = Right.load(
            "0xc190cb0662ae5a22f944557b2c0063cbcc6091ee",
            BlockChain.web3jInstance,
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        val newBalance = AccountServiceImpl().getBalance(BlockChain.bankAddress!!,Convert.Unit.WEI)

        logger.info("Call Result:${right.serialize().send()}")
        logger.info("cost ${oldBalance - newBalance} wei.")
    }
}