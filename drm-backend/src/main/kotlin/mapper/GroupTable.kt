package moe._47saikyo.mapper

import constant.GlobalConstant
import domain.Group
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * 用户组表映射类
 * CREATE TABLE `drm_group` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `group_name` varchar(128) NOT NULL,
 *   `permission_show_profile` tinyint(1) NOT NULL DEFAULT 0,
 *   `permission_login` tinyint(1) NOT NULL DEFAULT 0,
 *   `permission_create_right` tinyint(1) NOT NULL DEFAULT 0,
 *   `permission_create_license` tinyint(1) NOT NULL DEFAULT 0,
 *   `permission_create_chain_account` tinyint(1) DEFAULT 0,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `group_name` (`group_name`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 *
 * @author 刘一邦
 * @since 2024/01/07
 */
object GroupTable : Table("drm_group") {
    val id = long("id").autoIncrement()
    val group_name = varchar("group_name", 128).uniqueIndex()
    val permission_login = bool("permission_login")
    val permission_show_profile = bool("permission_show_profile")
    val permission_create_right = bool("permission_create_right")
    val permission_create_license = bool("permission_create_license")
    val permission_create_chain_account = bool("permission_create_chain_account")
    val permission_verify_right = bool("permission_verify_right")
    override val primaryKey = PrimaryKey(id)

    fun resultRowToGroup(row: ResultRow) = Group(
        id = row[id],
        groupName = row[group_name],
        permissionLogin = row[permission_login],
        permissionShowProfile = row[permission_show_profile],
        permissionCreateRight = row[permission_create_right],
        permissionCreateLicense = row[permission_create_license],
        permissionCreateChainAccount = row[permission_create_chain_account],
        permissionVerifyRight = row[permission_verify_right]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(group: Group): GroupTable.(statement: T) -> Unit = {
        it[group_name] = group.groupName ?: GlobalConstant.NULL_PLACEHOLDER
        it[permission_login] = group.permissionLogin
        it[permission_show_profile] = group.permissionShowProfile
        it[permission_create_right] = group.permissionCreateRight
        it[permission_create_license] = group.permissionCreateLicense
        it[permission_create_chain_account] = group.permissionCreateChainAccount
        it[permission_verify_right] = group.permissionVerifyRight
    }
}