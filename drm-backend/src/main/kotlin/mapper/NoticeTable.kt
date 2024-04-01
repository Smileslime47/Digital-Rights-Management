package moe._47saikyo.mapper

import constant.GlobalConstant
import domain.Notice
import enums.NoticeStatus
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * CREATE TABLE `drm_notice` (
 *  `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *  `title` varchar(128) NOT NULL,
 *  `content` text NOT NULL,
 *  `receiver_id` bigint(20) NOT NULL,
 *  `sent_time` bigint(20) NOT NULL,
 *  `status` varchar(128) NOT NULL,
 *  `target_route` varchar(128) DEFAULT NULL,
 *  PRIMARY KEY (`id`)
 *  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 *
 *  @author 刘一邦
 */
object NoticeTable : Table("drm_notice"){
    val id = long("id").autoIncrement()
    val title = varchar("title", 128)
    val content = text("content")
    val receiver_id = long("receiver_id")
    val sent_time = long("sent_time")
    val status = varchar("status", 128)
    val target_route = varchar("target_route", 128)
    override val primaryKey = PrimaryKey(id)

    fun resultRowToNotice(row: ResultRow) = Notice(
        id = row[id],
        title = row[title],
        content = row[content],
        receiverId = row[receiver_id],
        sentTime = row[sent_time],
        status = NoticeStatus.fromString(row[status]),
        targetRoute = row[target_route]
    )

    fun <T:UpdateBuilder<Int>> getStatementBinder(notice: Notice): NoticeTable.(T) -> Unit = {
        it[title] = notice.title
        it[content] = notice.content
        it[receiver_id] = notice.receiverId
        it[sent_time] = notice.sentTime
        it[status] = notice.status.toString()
        it[target_route] = notice.targetRoute?:GlobalConstant.EMPTY_STRING
    }
}