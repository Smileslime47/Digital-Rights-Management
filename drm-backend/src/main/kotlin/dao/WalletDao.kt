package moe._47saikyo.dao

import domain.Wallet
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

interface WalletDao {
    suspend fun getWallet(where: SqlExpressionBuilder.() -> Op<Boolean>): Wallet?
    suspend fun getWallets(): List<Wallet>
    suspend fun insertWallet(wallet: Wallet): Wallet?
    suspend fun updateWallet(wallet: Wallet): Boolean
    suspend fun deleteWallet(wallet: Wallet): Boolean
}