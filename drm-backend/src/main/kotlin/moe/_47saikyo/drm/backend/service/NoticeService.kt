package moe._47saikyo.drm.backend.service

import moe._47saikyo.drm.core.domain.Notice

/**
 * NoticeService接口
 *
 * @author 刘一邦
 */
interface NoticeService {
    /**
     * 获取通知
     *
     * @param id 通知ID
     * @return 通知
     */
    suspend fun getNotice(id: Long): Notice?

    /**
     * 计算通知数量(不包含归档)
     *
     * @param receiverId 接收者ID
     * @return 通知数量
     */
    suspend fun countNotices(receiverId: Long): Long

    /**
     * 获取通知(不包含归档)
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param receiverId 接收者ID
     * @return 通知
     */
    suspend fun getNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>

    /**
     * 计算未读通知数量
     *
     * @param receiverId 接收者ID
     * @return 未读通知数量
     */
    suspend fun countUnreadNotices(receiverId: Long): Long

    /**
     * 获取未读通知
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param receiverId 接收者ID
     */
    suspend fun getUnreadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>

    /**
     * 计算已读通知数量
     *
     * @param receiverId 接收者ID
     * @return 已读通知数量
     */
    suspend fun countReadNotices(receiverId: Long): Long

    /**
     * 获取已读通知
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param receiverId 接收者ID
     */
    suspend fun getReadNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>

    /**
     * 计算归档通知数量
     *
     * @param receiverId 接收者ID
     * @return 归档通知数量
     */
    suspend fun countArchivedNotices(receiverId: Long): Long

    /**
     * 获取归档通知
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param receiverId 接收者ID
     */
    suspend fun getArchivedNotices(pageSize: Int, pageNumber: Int, receiverId: Long): List<Notice>

    /**
     * 插入通知
     *
     * @param notice 通知
     * @return 通知
     */
    suspend fun insertNotice(notice: Notice): Notice?

    /**
     * 更新通知
     *
     * @param notice 通知
     * @return 是否更新成功
     */
    suspend fun updateNotice(notice: Notice): Boolean
}