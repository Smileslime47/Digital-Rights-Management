package moe._47saikyo

import moe._47saikyo.contract.DRManager
import moe._47saikyo.exception.BlockChainNotConnectedException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.admin.Admin
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGetTransactionCount
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigInteger

/**
 * 区块链链接维护管理类
 *
 * @author 刘一邦
 */
object BlockChain {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    //连接状态
    var connected = false
        private set

    //区块链ID
    var chainId = 0L
        private set

    //Web3j与以太坊客户端的链接，开启Admin功能以启用账号管理
    var web3jInstance: Admin? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get web3j instance.")
        } else field

    //银行账户地址
    var bankAddress: String? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get bankAddress.")
        } else field

    //银行账户凭证
    var bankCredentials: Credentials? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get Credentials.")
        } else field

    /**
     * 建议在任何时候使用txManager而非credentials来发出交易
     * 如果使用不带有chainId的credential发出交易，当geth不带有--rpc.allow-unprotected-txs启动时，会报出only replay-protected (EIP-155) transactions allowed over RPC错误
     * （https://ethereum.stackexchange.com/questions/121003/error-while-using-erc-20-java-library-only-replay-protected-eip-155-transact）
     */
    var bankTxManager: TransactionManager? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get TransactionManager.")
        } else field

    //DRManager地址
    var managerAddr: String = ""
        private set

    //GasProvider
    var gasProvider: StaticGasProvider = DefaultGasProvider()
        private set

    /**
     * 连接到以太坊客户端
     *
     * @param configuration 链接配置
     */
    fun connect(configuration: BlockChainConfiguration) {
        try {
            //建立连接
            web3jInstance = Admin.build(HttpService(configuration.socket))

            //加载银行账户
            bankCredentials = WalletUtils.loadCredentials(configuration.walletPassword, configuration.walletSource)
            bankAddress = bankCredentials!!.address
            bankTxManager = RawTransactionManager(
                web3jInstance, bankCredentials, configuration.chainId
            )

            //设置GasProvider
            gasProvider = StaticGasProvider(configuration.gasPrice, configuration.gasLimit)

            //加载或部署DRManager合约
            if (configuration.managerAddress == null) {
                managerAddr = DRManager.deploy(web3jInstance, bankTxManager, gasProvider).send().contractAddress
                logger.info("Deployed DRManager contract at $managerAddr")
            } else {
                managerAddr = DRManager.load(configuration.managerAddress, web3jInstance, bankTxManager, gasProvider).contractAddress
                logger.info("Loaded DRManager contract at $managerAddr")
            }

            //设置连接状态
            connected = true
            this.chainId = configuration.chainId

        } catch (e: Exception) {
            throw BlockChainNotConnectedException(e.message)
        }
    }

    /**
     * 获取下一个nonce
     *
     * @param walletAddress 钱包地址
     * @return 下一个nonce
     */
    fun getNextNonce(walletAddress: String): BigInteger {
        if (!connected) throw BlockChainNotConnectedException("Block chain not connected.")
        val ethGetTransactionCount: EthGetTransactionCount = web3jInstance!!.ethGetTransactionCount(
            walletAddress, DefaultBlockParameterName.LATEST
        ).sendAsync().get()

        return ethGetTransactionCount.transactionCount
    }
}