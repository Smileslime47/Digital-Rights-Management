package moe._47saikyo

import moe._47saikyo.exception.BlockChainNotConnectedException
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager


object BlockChain {
    var connected = false
        private set
    var web3jInstance: Web3j? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get web3j instance.")
        } else field

    var credentials: Credentials? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get Credentials.")
        } else field

    /**
     * 建议在任何时候使用txManager而非credentials来发出交易
     * 如果使用不带有chainId的credential发出交易，当geth不带有
     * （https://ethereum.stackexchange.com/questions/121003/error-while-using-erc-20-java-library-only-replay-protected-eip-155-transact）
     */
    var txManager: TransactionManager? = null
        private set
        get() = if (field == null) {
            throw BlockChainNotConnectedException("Block chain not connected,can not get TransactionManager.")
        } else field

    fun connect(socket: String, password: String, walletSource: String, chainId: Long) {
        try {
            web3jInstance = Web3j.build(HttpService(socket))
            credentials = WalletUtils.loadCredentials(password, walletSource)
            txManager = RawTransactionManager(
                web3jInstance, credentials, chainId
            )

            connected = true
        } catch (e: Exception) {
            throw BlockChainNotConnectedException(e.message)
        }
    }
}