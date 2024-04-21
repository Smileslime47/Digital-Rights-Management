package moe._47saikyo.drm.core.domain

import moe._47saikyo.drm.core.constant.GlobalConstant

data class Wallet(
    var id: Long = 0,
    var userId: Long = 0,
    var address: String = GlobalConstant.EMPTY_STRING,
    var walletFile: String = GlobalConstant.EMPTY_STRING,
    var cipherIv: String = GlobalConstant.EMPTY_STRING
)