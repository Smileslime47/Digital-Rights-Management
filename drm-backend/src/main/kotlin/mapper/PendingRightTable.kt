package moe._47saikyo.mapper

import domain.PendingRight
import domain.PendingStatus
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * CREATE TABLE `drm_pending_right` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `title` varchar(128) NOT NULL,
 *   `owner` varchar(128) NOT NULL,
 *   `registration_number` varchar(128) NOT NULL,
 *   `issue_time` bigint(20) NOT NULL,
 *   `expire_time` bigint(20) NOT NULL,
 *   `description` text DEFAULT NULL,
 *   `status` varchar(128) NOT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 *
 * @author 刘一邦
 * @since 2024/01/07
 */
object PendingRightTable : Table("drm_pending_right") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 128)
    val owner = varchar("owner", 128)
    val registration_number = varchar("registration_number", 128)
    val issue_time = long("issue_time")
    val expire_time = long("expire_time")
    val description = text("description")
    val status = varchar("status", 128)
    override val primaryKey = PrimaryKey(id)

    fun resultRowToPendingRight(row: ResultRow) = PendingRight(
        id = row[id],
        title = row[title],
        owner = row[owner],
        registrationNumber = row[registration_number],
        issueTime = row[issue_time],
        expireTime = row[expire_time],
        description = row[description],
        status = PendingStatus.fromString(row[status]),
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(pendingRight: PendingRight): PendingRightTable.(T) -> Unit = {
        it[title] = pendingRight.title
        it[owner] = pendingRight.owner
        it[registration_number] = pendingRight.registrationNumber
        it[issue_time] = pendingRight.issueTime
        it[expire_time] = pendingRight.expireTime
        it[description] = pendingRight.description
        it[status] = PendingStatus.toString(pendingRight.status)
    }
}