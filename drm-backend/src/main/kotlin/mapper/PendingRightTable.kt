package moe._47saikyo.mapper


import moe._47saikyo.domain.PendingRight
import moe._47saikyo.enums.PendingStatus
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
 *   `file_name` varchar(128) NOT NULL,
 *   `file_hash` varchar(128) NOT NULL,
 *   `status` varchar(128) NOT NULL,
 *   `estimate_price` bigint(20),
 *   `comment` text DEFAULT NULL,
 *   `create_time` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `REGISTRATION_NUMBER_UK` (`registration_number`),
 *   UNIQUE KEY `FILE_HASH_UK` (`file_hash`),
 *   KEY `TITLE_INDEX` (`title`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 * @author 刘一邦
 * @since 2024/01/07
 */
object PendingRightTable : Table("drm_pending_right") {
    val id                      = long("id").autoIncrement()
    val title                   = varchar("title", 128)
    val deployer                = varchar("deployer", 128)
    val owner                   = varchar("owner", 128)
    val registration_number     = varchar("registration_number", 128).uniqueIndex()
    val issue_time              = long("issue_time")
    val expire_time             = long("expire_time")
    val description             = text("description").nullable()
    val file_name               = varchar("file_name", 128)
    val file_hash               = varchar("file_hash", 128).uniqueIndex()
    val status                  = varchar("status", 128)
    val estimate_price          = long("estimate_price").nullable()
    val comment                 = text("comment").nullable()
    val create_time             = long("create_time")
    override val primaryKey = PrimaryKey(id)

    fun resultRowToPendingRight(row: ResultRow) = PendingRight(
        id = row[id],
        title = row[title],
        deployer = row[deployer],
        owner = row[owner],
        registrationNumber = row[registration_number],
        issueTime = row[issue_time],
        expireTime = row[expire_time],
        description = row[description],
        fileName = row[file_name],
        fileHash = row[file_hash],
        status = PendingStatus.fromString(row[status]),
        estimatePrice = row[estimate_price],
        comment = row[comment],
        createTime = row[create_time]
    )

    fun <T : UpdateBuilder<Int>> getStatementBinder(pendingRight: PendingRight): PendingRightTable.(T) -> Unit = {
        it[title] = pendingRight.title
        it[deployer] = pendingRight.deployer
        it[owner] = pendingRight.owner
        it[registration_number] = pendingRight.registrationNumber
        it[issue_time] = pendingRight.issueTime
        it[expire_time] = pendingRight.expireTime
        it[description] = pendingRight.description
        it[file_name] = pendingRight.fileName
        it[file_hash] = pendingRight.fileHash
        it[status] = PendingStatus.toString(pendingRight.status)
        it[estimate_price] = pendingRight.estimatePrice
        it[comment] = pendingRight.comment
        it[create_time] = pendingRight.createTime
    }
}