package moe._47saikyo.drm.backend.dao

import moe._47saikyo.drm.core.domain.Notice
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

interface NoticeDao {
    suspend fun countNotice(where: SqlExpressionBuilder.() -> Op<Boolean>): Long
    suspend fun getNotice(where: SqlExpressionBuilder.() -> Op<Boolean>): Notice?
    suspend fun getNotices(where: SqlExpressionBuilder.() -> Op<Boolean>): List<Notice>
    suspend fun getNotices(pageSize: Int, pageNumber: Int, where: SqlExpressionBuilder.() -> Op<Boolean>): List<Notice>
    suspend fun insertNotice(notice: Notice): Notice?
    suspend fun updateNotice(notice: Notice): Boolean
    suspend fun deleteNotice(notice: Notice): Boolean
}