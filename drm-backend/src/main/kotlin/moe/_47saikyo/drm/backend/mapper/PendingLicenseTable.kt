package moe._47saikyo.drm.backend.mapper

import moe._47saikyo.drm.core.domain.PendingLicense
import moe._47saikyo.drm.core.enums.PendingStatus
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * CREATE TABLE `drm_pending_license` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `right` varchar(128) NOT NULL,
 *   `deployer` varchar(128) NOT NULL,
 *   `owner` varchar(128) NOT NULL,
 *   `issue_time` bigint(20) NOT NULL,
 *   `expire_time` bigint(20) NOT NULL,
 *   `description` text DEFAULT NULL,
 *   `status` varchar(128) NOT NULL,
 *   `estimate_price` bigint(20) DEFAULT NULL,
 *   `comment` text DEFAULT NULL,
 *   `create_time` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 * @author 刘一邦
 */
object PendingLicenseTable: Table("drm_pending_license"){
    val id                      = long("id").autoIncrement()
    val right_title             = varchar("right_title", 128)
    val right_deployer          = varchar("right_deployer", 128)
    val right_index             = long("right_index")
    val deployer                = varchar("deployer", 128)
    val owner                   = varchar("owner", 128)
    val issue_time              = long("issue_time")
    val expire_time             = long("expire_time")
    val description             = text("description").nullable()
    val status                  = varchar("status", 128)
    val estimate_price          = long("estimate_price").nullable()
    val comment                 = text("comment").nullable()
    val create_time             = long("create_time")
    override val primaryKey = PrimaryKey(id)

    fun resultRowToPendingLicense(row: ResultRow) = PendingLicense(
        id = row[id],
        rightTitle = row[right_title],
        rightDeployer = row[right_deployer],
        rightIndex = row[right_index],
        deployer = row[deployer],
        owner = row[owner],
        issueTime = row[issue_time],
        expireTime = row[expire_time],
        description = row[description],
        status = PendingStatus.valueOf(row[status]),
        estimatePrice = row[estimate_price],
        comment = row[comment],
        createTime = row[create_time],
    )

    fun <T : UpdateBuilder<Int>>getStatementBinder(pendingLicense: PendingLicense): PendingLicenseTable.(T) -> Unit = {
        it[right_title] = pendingLicense.rightTitle
        it[right_deployer] = pendingLicense.rightDeployer
        it[right_index] = pendingLicense.rightIndex
        it[deployer] = pendingLicense.deployer
        it[owner] = pendingLicense.owner
        it[issue_time] = pendingLicense.issueTime
        it[expire_time] = pendingLicense.expireTime
        it[description] = pendingLicense.description
        it[status] = pendingLicense.status.name
        it[estimate_price] = pendingLicense.estimatePrice
        it[comment] = pendingLicense.comment
        it[create_time] = pendingLicense.createTime
    }
}