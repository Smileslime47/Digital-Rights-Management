package moe._47saikyo.mapper

import constant.GlobalConstant
import domain.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * 用户表映射类
 * CREATE TABLE `drm_user` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `permission_id` bigint(20) NOT NULL,
 *   `username` varchar(128) NOT NULL,
 *   `nickname` varchar(128) NOT NULL,
 *   `password` varchar(128) NOT NULL,
 *   `email` varchar(128) DEFAULT NULL,
 *   `phone_number` varchar(128) DEFAULT NULL,
 *   `chain_address` varchar(128) DEFAULT NULL,
 *   `chain_wallet_file` varchar(1024) DEFAULT NULL,
 *   `chain_cipher_iv` varchar(128) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `user_pk_username` (`username`),
 *   UNIQUE KEY `user_pk_chain_address` (`chain_address`),
 *   KEY `drm_user_group_id_fk` (`permission_id`),
 *   CONSTRAINT `drm_user_group_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `drm_group` (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
object UserTable : Table("drm_user") {
    val id = long("id").autoIncrement()
    val permission_id = long("permission_id")
    val username = varchar("username", 128).uniqueIndex()
    val password = varchar("password", 128)
    val nickname = varchar("nickname", 128)
    val email = varchar("email", 128)
    val phone_number = varchar("phone_number", 128)
    val chain_address = varchar("chain_address", 128).uniqueIndex()
    val chain_wallet_file = varchar("chain_wallet_file", 1024)
    val chain_cipher_iv = varchar("chain_cipher_iv", 128)
    override val primaryKey = PrimaryKey(id)

    fun resultRowToUser(row: ResultRow) = User(
        id = row[id],
        permissionId = row[permission_id],
        username = row[username],
        password = row[password],
        nickname = row[nickname],
        email = row[email],
        phoneNumber = row[phone_number],
        chainAddress = row[chain_address],
        chainWalletFile = row[chain_wallet_file],
        chainCipherIv = row[chain_cipher_iv]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(user: User): UserTable.(statement: T) -> Unit = {
        it[permission_id] = user.permissionId
        it[username] = user.username
        it[password] = user.password
        it[nickname] = user.nickname
        it[email] = user.email ?: GlobalConstant.NULL_PLACEHOLDER
        it[phone_number] = user.phoneNumber ?: GlobalConstant.NULL_PLACEHOLDER
        it[chain_address] = user.chainAddress ?: GlobalConstant.NULL_PLACEHOLDER
        it[chain_wallet_file] = user.chainWalletFile ?: GlobalConstant.NULL_PLACEHOLDER
        it[chain_cipher_iv] = user.chainCipherIv ?: GlobalConstant.NULL_PLACEHOLDER
    }
}