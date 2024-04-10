package moe._47saikyo

import moe._47saikyo.constant.GlobalConstant
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

/**
 * 区块链配置Builder
 *
 * @author 刘一邦
 */
class BlockChainConfigurationBuilder {
    //以太坊端点地址
    var socket: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //银行账户的KeyFile路径
    var walletSource: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //银行账户密码
    var walletPassword: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //GasPrice
    var gasPrice: BigInteger = DefaultGasProvider().gasPrice
        private set

    //GasLimit
    var gasLimit: BigInteger = DefaultGasProvider().gasLimit
        private set

    //区块链的链ID,用于开启txManager
    var chainId: Long = 0L
        private set

    //DRManager地址
    var managerAddress: String? = null
        private set

    fun withChain(socket: String,chainId: Long): BlockChainConfigurationBuilder {
        this.socket = socket
        this.chainId = chainId
        return this
    }

    fun withBankWallet(source: String, password: String): BlockChainConfigurationBuilder {
        this.walletSource = source
        this.walletPassword = password
        return this
    }

    fun withGasProvider(gasPrice: BigInteger, gasLimit: BigInteger): BlockChainConfigurationBuilder {
        this.gasPrice = gasPrice
        this.gasLimit = gasLimit
        return this
    }

    fun withManager(addr: String): BlockChainConfigurationBuilder {
        this.managerAddress = addr
        return this
    }

    fun build():BlockChainConfiguration{
        return BlockChainConfiguration(this)
    }
}