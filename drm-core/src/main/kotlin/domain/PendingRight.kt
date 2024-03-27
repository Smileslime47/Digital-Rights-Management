package domain

data class PendingRight(
    val id:Long,
    val title: String,
    val owner: String,
    val registrationNumber: String,
    val issueTime: Long,
    val expireTime: Long,
    val description: String,
    var status: PendingStatus
)