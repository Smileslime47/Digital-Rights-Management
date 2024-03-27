package moe._47saikyo.dao.impl

import domain.Notice
import moe._47saikyo.dao.NoticeDao
import moe._47saikyo.mapper.NoticeTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * NoticeDao实现
 *
 * @author 刘一邦
 */
class NoticeDaoImpl : NoticeDao {
    override suspend fun countNotice(where: SqlExpressionBuilder.() -> Op<Boolean>): Long =
        transaction { NoticeTable.select(where).count() }
    override suspend fun getNotice(where: SqlExpressionBuilder.() -> Op<Boolean>): Notice? =
        transaction { NoticeTable.select(where).map(NoticeTable::resultRowToNotice).singleOrNull() }

    override suspend fun getNotices(where: SqlExpressionBuilder.() -> Op<Boolean>): List<Notice> =
        transaction { NoticeTable.select(where).map(NoticeTable::resultRowToNotice) }

    override suspend fun getNotices(
        pageSize: Int,
        pageNumber: Int,
        where: SqlExpressionBuilder.() -> Op<Boolean>
    ): List<Notice> =
        transaction {
            NoticeTable.select(where).limit(pageSize, (pageNumber - 1) * pageSize.toLong())
                .map(NoticeTable::resultRowToNotice)
        }

    override suspend fun insertNotice(notice: Notice): Notice? =
        transaction {
            NoticeTable.insert(NoticeTable.getStatementBinder(notice)).resultedValues?.singleOrNull()
                ?.let(NoticeTable::resultRowToNotice)
        }

    override suspend fun updateNotice(notice: Notice): Boolean =
        transaction {
            NoticeTable.update(
                { NoticeTable.id eq notice.id },
                null,
                NoticeTable.getStatementBinder(notice)
            ) > 0
        }

    override suspend fun deleteNotice(notice: Notice): Boolean =
        transaction { NoticeTable.deleteWhere { id eq notice.id } > 0 }
}