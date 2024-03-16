package moe._47saikyo.blockchain

import constant.GlobalConstant
import kotlinx.serialization.Serializable
import org.h2.security.SHA256.getHash
import java.security.MessageDigest

@Serializable
data class Block(
    val index: Long = 0L,
    val hash: String = "",
    val previousHash: String? = "",
    val timestamp: Long = 0,
    val data: String? = ""
)