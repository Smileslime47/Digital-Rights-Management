package moe._47saikyo.service

import moe._47saikyo.BlockChain
import moe._47saikyo.service.impl.AccountServiceImpl
import moe._47saikyo.utils.CryptoUtils
import org.web3j.crypto.WalletFile
import org.web3j.tx.TransactionManager
import java.math.BigDecimal
import java.math.BigInteger


/**
 * 以太坊账户Service接口
 *
 * @author 刘一邦
 */
interface AccountService {
    /**
     * 通过密码和随机生成的密钥对创建WalletFile,并将KeyFile以WalletFile对象返回
     *
     * 由于KeyFile的特殊性，在考虑中心化存储该文件的情况下请考虑加密存储
     * @see [CryptoUtils]
     *
     * @param password 新账户的密码
     * @return KeyFile文件
     */
    fun newAccount(password: String):WalletFile

    /**
     * 通过BlockChain的Web3j实例创建一个新的区块链账户
     *
     * 注：不建议使用该接口，KeyFile会直接生成在区块链服务器的KeyStore文件夹下，导致账户难以管理
     *
     * @see [AccountService.newAccount]
     *
     * @param password 新账户的密码
     * @return 新账户的哈希地址（公钥的后二十位）
     */
    @Deprecated("Use newAccount instead")
    fun newAccountByPersonal(password: String): String

    /**
     * 获取区块链指定账户余额
     *
     * @param addr 查询账户地址
     * @return 账户余额（单位为Wei——1Wei = 1e-18 Eth）
     */
    fun getBalance(addr: String): BigDecimal

    /**
     * 通过银行账户向指定账户转账
     * 调用[BlockChain.connect]时传入的WalletSource即为银行账户
     *
     * @param addr 收款账户地址
     * @param value 收款金额
     * @return 操作结果
     */
    fun chargeFromBank(addr: String, value: String): Boolean

    /**
     * 获取指定区块链账户的TransactionManager
     * 用于批准部署智能合约
     *
     * @param pwd 区块链账户密码
     * @param keyFileContent 区块链账户的KeyFile文件内容，应当为序列化的Json字符串
     * @return 指定账户的TransactionManager
     */
    fun getTxManager(pwd: String, keyFileContent: String): TransactionManager
}