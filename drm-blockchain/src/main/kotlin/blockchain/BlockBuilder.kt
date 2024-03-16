package moe._47saikyo.blockchain


class BlockBuilder(
    private val builderIndex: Long? = null,
    private val builderPreviousHash: String? = null,
    private val builderData: String? = null
) {
    fun withIndex(newIndex: Long): BlockBuilder =
        BlockBuilder(newIndex, builderPreviousHash, builderData)

    fun withPreviousHash(newPreviousHash: String): BlockBuilder =
        BlockBuilder(builderIndex, newPreviousHash, builderData)

    fun withData(newData: String): BlockBuilder =
        BlockBuilder(builderIndex, builderPreviousHash, newData)

    fun generate(): Block? {
        if (builderIndex === null) return null

        val builderTimestamp = System.currentTimeMillis()
        val builderHash =
            HashUtils.bytesToHex(HashUtils.getHash(builderIndex.toString() + builderPreviousHash + builderTimestamp + builderData))

        return Block(
            index = builderIndex,
            previousHash = builderPreviousHash,
            data = builderData,
            timestamp = builderTimestamp,
            hash = builderHash
        )
    }
}