package moe._47saikyo.domain

import moe._47saikyo.enums.PendingStatus

data class PendingRight(
    val id:Long,
    val title: String,
    val owner: String,
    val registrationNumber: String,
    val issueTime: Long,
    val expireTime: Long,
    val description: String? = null,
    val fileName: String,
    val fileHash: String,
    var status: PendingStatus,
    var estimatePrice:Long? = null,
    var comment:String? = null,
    var createTime:Long
)