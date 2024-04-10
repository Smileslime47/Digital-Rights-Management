package moe._47saikyo

import moe._47saikyo.constant.GlobalConstant
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

/**
 * 区块链配置
 *
 * @author 刘一邦
 */
data class BlockChainConfiguration (
    //以太坊端点地址
    val socket:         String = GlobalConstant.NULL_PLACEHOLDER,
    //银行账户的KeyFile路径
    val walletSource:   String = GlobalConstant.NULL_PLACEHOLDER,
    //银行账户密码
    val walletPassword: String = GlobalConstant.NULL_PLACEHOLDER,
    //GasPrice
    val gasPrice:       BigInteger = DefaultGasProvider().gasPrice,
    //GasLimit
    val gasLimit:       BigInteger = DefaultGasProvider().gasLimit,
    //区块链的链ID,用于开启txManager
    val chainId:        Long = 0L,
    //DRManager地址
    val managerAddress: String? = null
){
    constructor(builder: BlockChainConfigurationBuilder) : this(
        builder.socket,
        builder.walletSource,
        builder.walletPassword,
        builder.gasPrice,
        builder.gasLimit,
        builder.chainId,
        builder.managerAddress)
}