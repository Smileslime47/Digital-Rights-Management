package domain

data class Group(
    var id: Long = 0,
    var groupName: String? = null,
    var permissionLogin: Boolean = false,
    var permissionShowProfile: Boolean = false,
)