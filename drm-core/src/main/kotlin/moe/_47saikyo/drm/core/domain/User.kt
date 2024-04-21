package moe._47saikyo.drm.core.domain

import moe._47saikyo.drm.core.constant.GlobalConstant

data class User(
    var id: Long = 0,
    var permissionId: Long = 0,
    var username: String = GlobalConstant.NULL_PLACEHOLDER,
    var nickname: String = GlobalConstant.NULL_PLACEHOLDER,
    var password: String = GlobalConstant.NULL_PLACEHOLDER,
    var email: String? = null,
    var phoneNumber: String? = null,
)