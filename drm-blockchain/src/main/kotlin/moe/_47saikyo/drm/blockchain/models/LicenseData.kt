package moe._47saikyo.drm.blockchain.models

import moe._47saikyo.drm.blockchain.contract.DRManager

data class LicenseData(
    val index: Long = 0,
    val rightTitle: String = "",
    val rightKeyPair: KeyPairData = KeyPairData(),
    val deployer: String = "",
    val owner: String = "",
    val issueTime: Long = 0,
    val expireTime: Long = 0,
    val description: String = "",
) {
    companion object {
        fun fromLicenseStruct(license: DRManager.License): LicenseData {
            return LicenseData(
                license.index.toLong(),
                license.rightTitle,
                KeyPairData.fromKeyPairStruct(license.rightKeyPair),
                license.deployer,
                license.owner,
                license.issueTime.toLong(),
                license.expireTime.toLong(),
                license.description
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LicenseData

        when {
//            index != other.index -> return false
            rightTitle != other.rightTitle -> return false
            rightKeyPair != other.rightKeyPair -> return false
            deployer != other.deployer -> return false
            owner != other.owner -> return false
            issueTime != other.issueTime -> return false
            expireTime != other.expireTime -> return false
            description != other.description -> return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = index.hashCode()
        result = 31 * result + rightTitle.hashCode()
        result = 31 * result + rightKeyPair.hashCode()
        result = 31 * result + deployer.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + issueTime.hashCode()
        result = 31 * result + expireTime.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}