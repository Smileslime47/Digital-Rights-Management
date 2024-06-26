package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.*
import moe._47saikyo.drm.blockchain.*
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.blockchain.constant.BlockChainConstant
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * RightServiceTest
 *
 * @author 刘一邦
 */
class RightServiceTest {
    private val logger = LoggerFactory.getLogger(RightServiceTest::class.java)
    private val accountService: AccountService by inject(AccountService::class.java)
    private val rightService:RightService by inject(RightService::class.java)

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
    fun getPureDataWithManagerTest() {
        //创建函数调用交易
        val function = Function("serialize", emptyList(), listOf(TypeReference.create(string::class.java)))

        val transaction = Transaction.createEthCallTransaction(
            BlockChain.bankAddress!!,
            "0x17ee7ffa7f79a43b51dc7e01ef924b0f541fdfeb",
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).send().value

        //获取函数返回值
        val json = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        //解析json并返回
        logger.info(json)
    }

    @Test
    fun testAddGas(){
        assert(
            Estimate.estimateCall(
                "0xcb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe",
                "0xef563dee888cb304dd660b9f6e6b261f1a2295d2",
                "addLicense",
                listOf(
                    address(BlockChainConstant.ADDRESS_PLACEHOLDER)
                ),
                emptyList()
            ) == BlockChainConstant.Gas.RIGHT_ADD//66014L
        )
    }
}