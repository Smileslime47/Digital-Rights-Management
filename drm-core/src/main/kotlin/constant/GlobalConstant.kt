package moe._47saikyo.constant

object GlobalConstant {
    const val NULL_PLACEHOLDER = "undefined"
    const val EMPTY_STRING = ""

    const val PENDING_STATUS_PENDING = "PENDING"
    const val PENDING_STATUS_CONFIRMED = "CONFIRMED"
    const val PENDING_STATUS_DEPLOYING = "DEPLOYING"
    const val PENDING_STATUS_DEPLOYED = "DEPLOYED"
    const val PENDING_STATUS_REJECTED = "REJECTED"

    val NOTICE_FILTERS = setOf(
        "ALL", "UNREAD", "READ", "ARCHIVED",
        "all", "unread", "read", "archived",
        "All", "Unread", "Read", "Archived"
    )
    const val NOTICE_STATUS_UNREAD = "UNREAD"
    const val NOTICE_STATUS_READ = "READ"
    const val NOTICE_STATUS_ARCHIVED = "ARCHIVED"
}