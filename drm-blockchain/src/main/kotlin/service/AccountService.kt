package moe._47saikyo.service

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
    fun newAccount(password: String):WalletFile
    fun newAccountByPersonal(password: String): String
    fun getBalance(addr: String): BigDecimal
    fun chargeFromBank(addr: String, value: String): Boolean
    fun getTxManager(pwd: String, keyFileContent: String): TransactionManager
}