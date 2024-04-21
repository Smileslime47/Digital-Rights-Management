package moe._47saikyo.drm.blockchain.models

data class RightData(
    val addr: String = "",
    val title: String = "",
    val deployer: String = "",
    val owner: String = "",
    val registrationNumber: String = "",
    val issueTime: Long = 0,
    val expireTime: Long = 0,
    val description: String = "",
    val fileName: String = "",
    val fileHash: String = "",
    val licenses: List<LicenseData> = emptyList(),
)