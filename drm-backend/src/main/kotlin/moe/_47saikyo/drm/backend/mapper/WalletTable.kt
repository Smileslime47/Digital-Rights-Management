package moe._47saikyo.drm.backend.mapper

import moe._47saikyo.drm.core.domain.Wallet
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * CREATE TABLE `drm_wallet` (
 *   `id` bigint(20) NOT NULL,
 *   `address` varchar(128) NOT NULL,
 *   `wallet_file` varchar(1024) NOT NULL,
 *   `cipher_iv` varchar(128) NOT NULL,
 *   `user_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `drm_wallet_pk_2` (`address`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 * @author 刘一邦
 */
object WalletTable : Table("drm_wallet"){
    val id = long("id").autoIncrement()
    val user_id = long("user_id")
    val address = varchar("address", 128)
    val wallet_file = varchar("wallet_file", 1024)
    val cipher_iv = varchar("cipher_iv", 128)
    override val primaryKey = PrimaryKey(id)

    fun <T: UpdateBuilder<Int>> getStatementBinder(wallet: Wallet): WalletTable.(T) -> Unit = {
        it[address] = wallet.address
        it[wallet_file] = wallet.walletFile
        it[cipher_iv] = wallet.cipherIv
        it[user_id] = wallet.userId
    }

    fun resultRowToWallet(row: ResultRow) = Wallet(
        id = row[id],
        address = row[address],
        walletFile = row[wallet_file],
        cipherIv = row[cipher_iv],
        userId = row[user_id]
    )
}