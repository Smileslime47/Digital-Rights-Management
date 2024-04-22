package moe._47saikyo.drm.blockchain.models

import moe._47saikyo.drm.blockchain.contract.DRManager
import java.math.BigInteger

data class KeyPairData (
    val deployer: String = "0",
    val arrayIndex: Long = 0
) {
    companion object {
        fun fromKeyPairStruct(keyPair: DRManager.KeyPair): KeyPairData {
            return KeyPairData(
                keyPair.deployer,
                keyPair.arrayIndex.toLong()
            )
        }
    }

    fun toKeyPairStruct(): DRManager.KeyPair {
        return DRManager.KeyPair(
            deployer,
            BigInteger.valueOf(arrayIndex)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KeyPairData

        when {
            deployer != other.deployer -> return false
            arrayIndex != other.arrayIndex -> return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = deployer.hashCode()
        result = 31 * result + arrayIndex.hashCode()
        return result
    }
}