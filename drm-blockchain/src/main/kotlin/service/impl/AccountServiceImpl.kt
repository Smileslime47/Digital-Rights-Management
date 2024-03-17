package moe._47saikyo.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import moe._47saikyo.BlockChain
import moe._47saikyo.exception.BlockChainNotConnectedException
import moe._47saikyo.service.AccountService
import org.web3j.crypto.Credentials
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet
import org.web3j.crypto.WalletFile
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import java.math.BigDecimal
import java.math.BigInteger

/**
 * 以太坊账户Service类
 *
 * @author 刘一邦
 */
class AccountServiceImpl : AccountService {
    /**
     * 通过密码和随机生成的密钥对创建WalletFile,并将KeyFile以序列化字符串形式返回
     *
     * @param password 新账户的密码
     * @return 序列化后的Json KeyFile文件
     */
    override fun newAccount(password: String): String {
        val wallerFile = Wallet.createStandard(password, Keys.createEcKeyPair())
        return ObjectMapper().writeValueAsString(wallerFile)
    }

    /**
     * 通过BlockChain的Web3j实例创建一个新的区块链账户
     *
     * 注：不建议使用该接口，KeyFile会直接生成在区块链服务器的KeyStore文件夹下，导致账户难以管理
     *
     * @see [AccountServiceImpl.newAccount]
     *
     * @param password 新账户的密码
     * @return 新账户的哈希地址（公钥的后二十位）
     */
    override fun newAccountByPersonal(password: String): String =
        if (!BlockChain.connected)
            throw BlockChainNotConnectedException("BlockChain not connected")
        else BlockChain.web3jInstance!!.personalNewAccount(password).send().result

    /**
     * 获取区块链指定账户余额
     *
     * @param addr 查询账户地址
     * @return 账户余额（单位为Wei——1Wei = 1e-18 Eth）
     */
    override fun getBalance(addr: String): BigInteger =
        if (!BlockChain.connected)
            throw BlockChainNotConnectedException("BlockChain not connected")
        else BlockChain.web3jInstance!!.ethGetBalance(addr, DefaultBlockParameterName.LATEST).send().balance

    /**
     * 通过银行账户向指定账户转账
     * 调用[BlockChain.connect]时传入的WalletSource即为银行账户
     *
     * @param addr 收款账户地址
     * @param value 收款金额
     * @return 操作结果
     */
    override fun chargeFromBank(addr: String, value: String): Boolean {
        try {
            Transfer(BlockChain.web3jInstance, BlockChain.bankTxManager)
                .sendFunds(addr, BigDecimal(value), Convert.Unit.WEI).send()

            return true
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * 获取指定区块链账户的TransactionManager
     * 用于批准部署智能合约
     *
     * @param pwd 区块链账户密码
     * @param keyFileContent 区块链账户的KeyFile文件内容，应当为序列化的Json字符串
     * @return 指定账户的TransactionManager
     */
    override fun getTxManager(pwd: String, keyFileContent: String): TransactionManager {
        val credentials = WalletUtils.loadJsonCredentials(pwd, keyFileContent)
        return RawTransactionManager(
            BlockChain.web3jInstance, credentials, BlockChain.chainId
        )
    }
    //以下为智能合约部署样例代码
//    override fun deploy(): String =
//        if (!BlockChain.connected) {
//            throw BlockChainNotConnectedException("BlockChain not connected")
//        } else Account.deploy(
//            BlockChain.web3jInstance,
//            BlockChain.txManager,
//            DefaultGasProvider()
//        ).send().contractAddress
}