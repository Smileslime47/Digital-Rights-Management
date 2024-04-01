package moe._47saikyo.service

import domain.Wallet
import moe._47saikyo.utils.CryptoUtils
import java.util.*

/**
 * WalletService接口
 *
 * @author 刘一邦
 */
interface WalletService {
    /**
     * 解密钱包
     *
     * @param wallet 钱包
     * @param phrase 密钥短语
     * @return 解密后的walletFile Json文本
     */
    fun decryptWallet(wallet:Wallet,phrase:String):String

    /**
     * 获取钱包
     *
     * @param userId 用户ID
     * @return 钱包
     */
    suspend fun getWallet(userId: Long): Wallet?

    /**
     * 获取钱包
     *
     * @param addr 钱包地址
     * @return 钱包
     */
    suspend fun getWallet(addr: String): Wallet?

    /**
     * 获取所有钱包
     *
     * @return 所有钱包
     */
    suspend fun getWallets(): List<Wallet>

    /**
     * 插入钱包
     *
     * @param wallet 钱包
     * @return 钱包
     */
    suspend fun insertWallet(wallet: Wallet): Wallet?

    /**
     * 更新钱包
     *
     * @param wallet 钱包
     * @return 是否成功
     */
    suspend fun updateWallet(wallet: Wallet): Boolean

    /**
     * 删除钱包
     *
     * @param wallet 钱包
     * @return 是否成功
     */
    suspend fun deleteWallet(wallet: Wallet): Boolean
}