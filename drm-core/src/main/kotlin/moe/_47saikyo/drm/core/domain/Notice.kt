package moe._47saikyo.drm.core.domain

import moe._47saikyo.drm.core.constant.GlobalConstant
import moe._47saikyo.drm.core.enums.NoticeStatus

data class Notice(
    var id: Long = 0,
    var title: String = GlobalConstant.NULL_PLACEHOLDER,
    var content: String = GlobalConstant.NULL_PLACEHOLDER,
    var receiverId: Long =0,
    var sentTime: Long = 0,
    var status: NoticeStatus = NoticeStatus.UNREAD,
    var targetRoute: String?
){
    constructor(
        title: String,
        content: String,
        receiverId: Long,
        targetRoute: String?
    ): this(0, title, content, receiverId, System.currentTimeMillis(), NoticeStatus.UNREAD, targetRoute)
}