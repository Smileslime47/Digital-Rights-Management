package moe._47saikyo.service.impl

import moe._47saikyo.domain.Wallet
import moe._47saikyo.dao.WalletDao
import moe._47saikyo.mapper.WalletTable
import moe._47saikyo.service.WalletService
import moe._47saikyo.utils.CryptoUtils
import org.koin.java.KoinJavaComponent.inject
import java.util.*

/**
 * WalletService实现
 *
 * @author 刘一邦
 */
class WalletServiceImpl : WalletService {
    private val walletDao: WalletDao by inject(WalletDao::class.java)

    override fun decryptWallet(wallet: Wallet, phrase: String): String {
        val decoder = Base64.getDecoder()
        val base64Wallet = wallet.walletFile
        val base64Iv = wallet.cipherIv

        //解码钱包文件
        val encryptedWalletFile = decoder.decode(base64Wallet)
        val iv = decoder.decode(base64Iv)
        val secretKey = CryptoUtils.getSecretKey(phrase)

        //解密钱包文件
        return String(
            CryptoUtils.decrypt(
                data = encryptedWalletFile,
                secretKey = secretKey,
                iv = iv
            )
        )
    }

    override suspend fun getWallet(userId: Long): Wallet? =
        walletDao.getWallet { WalletTable.user_id eq userId }

    override suspend fun getWallet(addr: String): Wallet? =
        walletDao.getWallet { WalletTable.address eq addr }

    override suspend fun getWallets(): List<Wallet> =
        walletDao.getWallets()

    override suspend fun insertWallet(wallet: Wallet): Wallet? {
        if(!wallet.address.startsWith("0x")){
            wallet.address = "0x${wallet.address}"
        }
        return walletDao.insertWallet(wallet)
    }

    override suspend fun updateWallet(wallet: Wallet): Boolean {
        if(!wallet.address.startsWith("0x")){
            wallet.address = "0x${wallet.address}"
        }
        return walletDao.updateWallet(wallet)
    }

    override suspend fun deleteWallet(wallet: Wallet): Boolean =
        walletDao.deleteWallet(wallet)
}