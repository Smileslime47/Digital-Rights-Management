package moe._47saikyo.service.impl

import domain.Wallet
import moe._47saikyo.dao.WalletDao
import moe._47saikyo.mapper.WalletTable
import moe._47saikyo.service.WalletService
import org.koin.java.KoinJavaComponent.inject

class WalletServiceImpl :WalletService{
    private val walletDao: WalletDao by inject(WalletDao::class.java)
    override suspend fun getWallet(userId: Long): Wallet? =
        walletDao.getWallet { WalletTable.user_id eq userId }

    override suspend fun getWallets(): List<Wallet> =
        walletDao.getWallets()

    override suspend fun insertWallet(wallet: Wallet): Wallet? =
        walletDao.insertWallet(wallet)

    override suspend fun updateWallet(wallet: Wallet): Boolean =
        walletDao.updateWallet(wallet)

    override suspend fun deleteWallet(wallet: Wallet): Boolean =
        walletDao.deleteWallet(wallet)
}