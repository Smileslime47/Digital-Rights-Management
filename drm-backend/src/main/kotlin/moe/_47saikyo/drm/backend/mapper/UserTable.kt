package moe._47saikyo.drm.backend.mapper

import moe._47saikyo.drm.core.constant.GlobalConstant
import moe._47saikyo.drm.core.domain.User
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
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `user_pk_username` (`username`),
 *   KEY `drm_user_group_id_fk` (`permission_id`),
 *   CONSTRAINT `drm_user_group_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `drm_group` (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
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
    override val primaryKey = PrimaryKey(id)

    fun resultRowToUser(row: ResultRow) = User(
        id = row[id],
        permissionId = row[permission_id],
        username = row[username],
        password = row[password],
        nickname = row[nickname],
        email = row[email],
        phoneNumber = row[phone_number],
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(user: User): UserTable.(statement: T) -> Unit = {
        it[permission_id] = user.permissionId
        it[username] = user.username
        it[password] = user.password
        it[nickname] = user.nickname
        it[email] = user.email ?: GlobalConstant.EMPTY_STRING
        it[phone_number] = user.phoneNumber ?: GlobalConstant.EMPTY_STRING
    }
}