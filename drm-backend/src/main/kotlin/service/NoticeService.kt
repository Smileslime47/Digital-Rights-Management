package moe._47saikyo.service

import domain.Notice

/**
 * NoticeService接口
 *
 * @author 刘一邦
 */
interface NoticeService {
    suspend fun getNotice(id: Long): Notice?
    suspend fun countNotices(receiverId: Long): Long
    suspend fun getNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>
    suspend fun countUnreadNotices(receiverId: Long): Long
    suspend fun getUnreadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>
    suspend fun countReadNotices(receiverId: Long): Long
    suspend fun getReadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>
    suspend fun countArchivedNotices(receiverId: Long): Long
    suspend fun getArchivedNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>
    suspend fun insertNotice(notice: Notice): Notice?
    suspend fun updateNotice(notice: Notice): Boolean
}