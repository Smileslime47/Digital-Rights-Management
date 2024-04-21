import moe._47saikyo.*
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.service.impl.AccountServiceImpl
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
        BlockChainTest.init()
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
            (Estimate.estimateDeploy(BlockChain.bankAddress!!,"$binCode$encodedConstructor").toLong() * BlockChain.gasProvider.gasPrice.toLong()).toString()
        )
    }

    /**
     * 测试调用合约的估算
     */
    @Test
    fun testEstimateCall() {
        logger.info(
            (Estimate.estimateCall(
                BlockChain.bankAddress!!,
                "0xfe5078c71a0fc05ab17429472fb2b310612706e5",
                "addRight",
                listOf(
                    address(BlockChainConstant.ADDRESS_PLACEHOLDER)
                ),
                listOf(
                    TypeReference.create(address::class.java)
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

        val function = Function("serialize", emptyList(), listOf(TypeReference.create(string::class.java)))
        val transaction = Transaction.createEthCallTransaction(
            BlockChain.bankAddress,
            "0x17ee7ffa7f79a43b51dc7e01ef924b0f541fdfeb",
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