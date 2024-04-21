package moe._47saikyo.drm.blockchain.models

import java.math.BigInteger

data class RightDeployForm(
    val title:String,
    val owner:String,
    val registrationNumber:String,
    val issueTime:BigInteger,
    val expireTime:BigInteger,
    val description:String?,
    val fileName:String,
    val fileHash:String,
)