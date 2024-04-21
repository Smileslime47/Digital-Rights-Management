package moe._47saikyo.domain

data class Group(
    var id: Long = 0,
    var groupName: String? = null,
    var permissionLogin: Boolean = false,
    var permissionShowProfile: Boolean = false,
    var permissionCreateChainAccount: Boolean = false,
    var permissionCreateRight: Boolean = false,
    var permissionVerifyRight: Boolean = false,
    var permissionCreateLicense: Boolean = false,
    var permissionVerifyLicense: Boolean = false,
)