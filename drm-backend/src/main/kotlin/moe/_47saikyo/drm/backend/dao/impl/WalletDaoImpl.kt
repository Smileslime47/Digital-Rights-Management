package moe._47saikyo.drm.backend.dao.impl

import moe._47saikyo.drm.backend.dao.WalletDao
import moe._47saikyo.drm.backend.mapper.WalletTable
import moe._47saikyo.drm.core.domain.Wallet
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * WalletDao实现
 *
 * @author 刘一邦
 */
class WalletDaoImpl: WalletDao {
    override suspend fun getWallet(where: SqlExpressionBuilder.() -> Op<Boolean>): Wallet? =
        transaction { WalletTable.select(where).map(WalletTable::resultRowToWallet).singleOrNull() }

    override suspend fun getWallets(): List<Wallet> =
        transaction { WalletTable.selectAll().map(WalletTable::resultRowToWallet) }

    override suspend fun insertWallet(wallet: Wallet): Wallet? =
        transaction {
            WalletTable.insert(WalletTable.getStatementBinder(wallet)).resultedValues?.singleOrNull()
                ?.let(WalletTable::resultRowToWallet)
        }

    override suspend fun updateWallet(wallet: Wallet): Boolean =
        transaction { WalletTable.update({ WalletTable.id eq wallet.id }, null, WalletTable.getStatementBinder(wallet)) > 0 }

    override suspend fun deleteWallet(wallet: Wallet): Boolean =
        transaction { WalletTable.deleteWhere { id eq wallet.id } > 0 }
}