package moe._47saikyo.models

import domain.Group
import moe._47saikyo.constant.Constant
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
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `group_name` (`group_name`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 * @author 刘一邦
 * @since 2024/01/07
 */
object Groups : Table("drm_group") {
    val id = long("id").autoIncrement()
    val group_name = varchar("group_name", 128).uniqueIndex()
    val permission_login = bool("permission_login")
    val permission_show_profile = bool("permission_show_profile")
    override val primaryKey = PrimaryKey(id)

    fun resultRowToGroup(row: ResultRow) = Group(
        id = row[id],
        groupName = row[group_name],
        permissionLogin = row[permission_login],
        permissionShowProfile = row[permission_show_profile]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(group: Group): Groups.(statement: T) -> Unit = {
        it[id] = group.id
        it[group_name] = group.groupName ?: Constant.Global.NULL_PLACEHOLDER
        it[permission_login] = group.permissionLogin
        it[permission_show_profile] = group.permissionShowProfile
    }
}