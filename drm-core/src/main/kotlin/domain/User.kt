package domain
data class User (
    var id: Long = 0,
    var permissionId: Long = 0,
    var username: String? = null,
    var nickname: String? = null,
    var password: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
)