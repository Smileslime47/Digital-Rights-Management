package moe._47saikyo.drm.blockchain.models

data class LicenseData(
    val addr: String = "",
    val rightTitle: String = "",
    val rightAddr: String = "",
    val deployer: String = "",
    val owner: String = "",
    val issueTime: Long = 0,
    val expireTime: Long = 0,
    val description: String = "",
)