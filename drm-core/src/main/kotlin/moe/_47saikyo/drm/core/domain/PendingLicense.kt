package moe._47saikyo.drm.core.domain

import moe._47saikyo.drm.core.enums.PendingStatus

data class PendingLicense(
    val id: Long,
    val rightTitle: String,
    val rightDeployer: String,
    val rightIndex: Long,
    val deployer: String,
    val owner: String,
    val issueTime: Long,
    val expireTime: Long,
    val description: String? = null,
    var status: PendingStatus,
    var estimatePrice: Long? = null,
    var comment: String? = null,
    var createTime: Long,
)