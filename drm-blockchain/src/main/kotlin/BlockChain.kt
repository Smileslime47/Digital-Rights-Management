package moe._47saikyo

import constant.GlobalConstant
import moe._47saikyo.exception.BlockChainNotConnectedException
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.admin.Admin
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager

/**
 * 区块链链接维护管理类
 *
 * @author 刘一邦
 */
object BlockChain {
    //连接状态
    var connected = false
        private set

    //区块链ID
    var chainId = 0L
        private set

    //DRManager地址
    var managerAddr = ""
        private set

    //Web3j与以太坊客户端的链接，开启Admin功能以启用账号管理
    var web3jInstance: Admin? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get web3j instance.")
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

    /**
     * 连接到以太坊客户端
     *
     * @param configuration 链接配置
     */
    fun connect(configuration: BlockChainConfiguration) {
        try {
            web3jInstance = Admin.build(HttpService(configuration.socket))
            bankCredentials = WalletUtils.loadCredentials(configuration.walletPassword, configuration.walletSource)
            bankTxManager = RawTransactionManager(
                web3jInstance, bankCredentials, configuration.chainId
            )

            connected = true
            this.chainId = configuration.chainId
            this.managerAddr = configuration.managerAddress ?: GlobalConstant.NULL_PLACEHOLDER
        } catch (e: Exception) {
            throw BlockChainNotConnectedException(e.message)
        }
    }
}