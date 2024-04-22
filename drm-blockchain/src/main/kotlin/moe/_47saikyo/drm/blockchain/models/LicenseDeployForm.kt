package moe._47saikyo.drm.blockchain.models

import java.math.BigInteger

data class LicenseDeployForm(
    val rightTitle: String,
    val rightKeyPairData: KeyPairData,
    val owner: String,
    val issueTime: BigInteger,
    val expireTime: BigInteger,
    val description: String,
)