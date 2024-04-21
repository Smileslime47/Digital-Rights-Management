package moe._47saikyo.drm.core.enums

import moe._47saikyo.drm.core.constant.GlobalConstant
import java.util.*

enum class NoticeStatus {
    UNREAD,
    READ,
    ARCHIVED,

    ;

    override fun toString(): String {
        return when (this) {
            UNREAD -> GlobalConstant.NOTICE_STATUS_UNREAD
            READ -> GlobalConstant.NOTICE_STATUS_READ
            ARCHIVED -> GlobalConstant.NOTICE_STATUS_ARCHIVED
        }
    }

    companion object {
        fun fromString(value: String): NoticeStatus {
            val upper = value.uppercase(Locale.getDefault())
            return when (upper) {
                GlobalConstant.NOTICE_STATUS_UNREAD -> UNREAD
                GlobalConstant.NOTICE_STATUS_READ -> READ
                GlobalConstant.NOTICE_STATUS_ARCHIVED -> ARCHIVED
                else -> throw IllegalArgumentException("Invalid value $value")
            }
        }
    }
}