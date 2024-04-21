package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.NoticeDao
import moe._47saikyo.drm.backend.mapper.NoticeTable
import moe._47saikyo.drm.backend.service.NoticeService
import moe._47saikyo.drm.core.domain.Notice
import moe._47saikyo.drm.core.enums.NoticeStatus
import org.jetbrains.exposed.sql.and
import org.koin.java.KoinJavaComponent.inject

/**
 * NoticeService实现
 *
 * @author 刘一邦
 */
class NoticeServiceImpl : NoticeService {
    private val noticeDao: NoticeDao by inject(NoticeDao::class.java)

    override suspend fun getNotice(id: Long): Notice? =
        noticeDao.getNotice { NoticeTable.id eq id }

    override suspend fun countNotices(receiverId: Long): Long =
        noticeDao.countNotice { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status neq NoticeStatus.ARCHIVED.toString()) }

    override suspend fun getNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice> =
        noticeDao.getNotices(
            pageSize,
            pageNumber
        ) { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status neq NoticeStatus.ARCHIVED.toString()) }

    override suspend fun countUnreadNotices(receiverId: Long): Long =
        noticeDao.countNotice { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.UNREAD.toString()) }

    override suspend fun getUnreadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice> =
        noticeDao.getNotices(
            pageSize,
            pageNumber
        ) { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.UNREAD.toString()) }

    override suspend fun countReadNotices(receiverId: Long): Long =
        noticeDao.countNotice { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.READ.toString()) }

    override suspend fun getReadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice> =
        noticeDao.getNotices(
            pageSize,
            pageNumber
        ) { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.READ.toString()) }

    override suspend fun countArchivedNotices(receiverId: Long): Long =
        noticeDao.countNotice { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.ARCHIVED.toString()) }

    override suspend fun getArchivedNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice> =
        noticeDao.getNotices(
            pageSize,
            pageNumber
        ) { (NoticeTable.receiver_id eq receiverId) and (NoticeTable.status eq NoticeStatus.ARCHIVED.toString()) }

    override suspend fun insertNotice(notice: Notice): Notice? =
        noticeDao.insertNotice(notice)

    override suspend fun updateNotice(notice: Notice): Boolean =
        noticeDao.updateNotice(notice)
}