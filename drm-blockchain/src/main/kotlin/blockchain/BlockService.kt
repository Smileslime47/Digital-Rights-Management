package moe._47saikyo.blockchain

class BlockService {
    val genesisBlock: Block
        get() = Block(
            index = 0L,
            hash = "4f204e9b914ca4359d992e413d6eba68307505de6d11cd470ea34bce8a0a5356",
            previousHash = null,
            timestamp = 1710160434677L,
            data = "genesisBlock"
        )
    private val lastBlock: Block
        get() = BlockDao.getAllBlocks().maxBy { it.index }

    fun nextBlock(data: String): Block? =
        BlockBuilder()
            .withIndex(lastBlock.index + 1)
            .withPreviousHash(lastBlock.hash)
            .withData(data)
            .generate()

    private fun validateHash(block: Block): Boolean =
        block.hash == HashUtils.bytesToHex(
            HashUtils.getHash(
                block.index.toString() + block.previousHash + block.timestamp + block.data
            )
        )

    fun validateBlock(block: Block): Boolean =
        block.index == lastBlock.index + 1 && block.previousHash == lastBlock.hash && validateHash(block)


}