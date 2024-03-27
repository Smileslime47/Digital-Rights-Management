package moe._47saikyo.service

import domain.Wallet

interface WalletService {
    suspend fun getWallet(userId: Long): Wallet?
    suspend fun getWallets(): List<Wallet>
    suspend fun insertWallet(wallet: Wallet): Wallet?
    suspend fun updateWallet(wallet: Wallet): Boolean
    suspend fun deleteWallet(wallet: Wallet): Boolean
}