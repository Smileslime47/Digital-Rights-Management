package moe._47saikyo.models

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
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `user_pk_2` (`username`),
 *   KEY `user_group_id_fk` (`permission_id`),
 *   CONSTRAINT `user_group_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `group` (`id`)
 * )
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
        phoneNumber = row[phone_number]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(user: User): UserTable.(statement: T) -> Unit = {
        it[permission_id] = user.permissionId
        it[username] = user.username ?: GlobalConstant.NULL_PLACEHOLDER
        it[password] = user.password ?: GlobalConstant.NULL_PLACEHOLDER
        it[nickname] = user.nickname ?: GlobalConstant.NULL_PLACEHOLDER
        it[email] = user.email ?: GlobalConstant.NULL_PLACEHOLDER
        it[phone_number] = user.phoneNumber ?: GlobalConstant.NULL_PLACEHOLDER
    }
}