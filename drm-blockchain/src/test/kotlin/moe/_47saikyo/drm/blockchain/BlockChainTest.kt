package moe._47saikyo.drm.blockchain

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
                .withChain("http://192.168.10.108:8546", 721)
                .withBankWallet(
                    "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/WalletFile.json",
                    "1234567890"
                )
                .withManager("0xbfe6ffcd87d4aa8d28239a1f5afbb22a3dd208f5")
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
                        "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-blockchain/src/test/resources/WalletFile.json",
                        "1234567890"
                    )
                    .withManager("0xbfe6ffcd87d4aa8d28239a1f5afbb22a3dd208f5")
                    .build()
            )
        }
    }
}