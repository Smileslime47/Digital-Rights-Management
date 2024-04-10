package moe._47saikyo.models

data class LicenseData(
    val addr: String = "",
    val right: String = "",
    val owner: String = "",
    val issueTime: Long = 0,
    val expireTime: Long = 0,
)