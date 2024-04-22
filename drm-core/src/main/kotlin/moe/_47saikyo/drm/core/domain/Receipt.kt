package moe._47saikyo.drm.core.domain

import moe._47saikyo.drm.core.constant.GlobalConstant

data class Receipt(
    val id: Long = 0,
    val status: String = GlobalConstant.NULL_PLACEHOLDER,
    val transactionHash: String = GlobalConstant.NULL_PLACEHOLDER,
    val blockHash: String = GlobalConstant.NULL_PLACEHOLDER,
    val blockNumber: Long = 0,
    val gasUsed: Long = 0,
    val deployer: String = GlobalConstant.NULL_PLACEHOLDER,
    val deployIndex: Long = 0,
    val createTime: Long = 0,
)