package moe._47saikyo.blockchain

import org.h2.security.SHA256
import kotlin.experimental.and

object HashUtils {
    fun getHash(str: String): ByteArray =
        SHA256.getHash(
            (str).toByteArray(),
            false
        )

    fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02x".format(it) }
    }
}