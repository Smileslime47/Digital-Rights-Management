package moe._47saikyo.drm.blockchain.models

import java.math.BigInteger

data class RightData(
    val index: Long = 0,
    val title: String = "",
    val deployer: String = "",
    val owner: String = "",
    val registrationNumber: String = "",
    val issueTime: Long = 0,
    val expireTime: Long = 0,
    val description: String = "",
    val fileName: String = "",
    val fileHash: String = "",
    val licenses: List<KeyPairData> = emptyList(),
){
    companion object{
        fun fromRightStruct(right: moe._47saikyo.drm.blockchain.contract.DRManager.Right): RightData {
            return RightData(
                right.index.toLong(),
                right.title,
                right.deployer,
                right.owner,
                right.registrationNumber,
                right.issueTime.toLong(),
                right.expireTime.toLong(),
                right.description,
                right.fileName,
                right.fileHash,
                right.licenses.map { KeyPairData.fromKeyPairStruct(it) }
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RightData

        when{
//            index != other.index -> return false
            title != other.title -> return false
            deployer != other.deployer -> return false
            owner != other.owner -> return false
            registrationNumber != other.registrationNumber -> return false
            issueTime != other.issueTime -> return false
            expireTime != other.expireTime -> return false
            description != other.description -> return false
            fileName != other.fileName -> return false
            fileHash != other.fileHash -> return false
//            licenses != other.licenses -> return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = index.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + deployer.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + registrationNumber.hashCode()
        result = 31 * result + issueTime.hashCode()
        result = 31 * result + expireTime.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + fileHash.hashCode()
        result = 31 * result + licenses.hashCode()
        return result
    }
}