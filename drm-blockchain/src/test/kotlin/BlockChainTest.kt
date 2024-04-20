import moe._47saikyo.BlockChain
import moe._47saikyo.BlockChainConfigurationBuilder
import kotlin.test.Test

/**
 * BlockChainTest
 *
 * @author 刘一邦
 */
class BlockChainTest {
    /**
     * 测试区块链连接
     */
    @Test
    fun test() {
        BlockChain.connect(
            BlockChainConfigurationBuilder()
                .withChain("http://192.168.10.108:8545", 721)
                .withBankWallet(
                    "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                    "1234567890"
                )
                .withManager("0x9c2cb0dc39e31484991efc461a9feab8ce0baf61")
                .build()
        )
        assert(BlockChain.connected)
    }

    companion object{
        /**
         * 单元测试统一初始化参数
         */
        fun init() {
            BlockChain.connect(
                BlockChainConfigurationBuilder()
                    .withChain("http://localhost:8545", 721)
                    .withBankWallet(
                        "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                        "1234567890"
                    )
                    .withManager("0xef563dee888cb304dd660b9f6e6b261f1a2295d2")
                    .build()
            )
        }
    }
}