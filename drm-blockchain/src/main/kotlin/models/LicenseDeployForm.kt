package moe._47saikyo.models

import java.math.BigInteger

data class LicenseDeployForm(
    val right: String,
    val owner: String,
    val issueTime: BigInteger,
    val expireTime: BigInteger,
    val description: String,
)