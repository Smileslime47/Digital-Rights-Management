package moe._47saikyo.constant

import java.math.BigInteger

/**
 * 区块链常量
 *
 * @author 刘一邦
 */
object BlockChainConstant {
    // 40位长度的地址占位符
    const val ADDRESS_PLACEHOLDER = "0xffffffffffffffffffffffffffffffffffffffff"

    // 调用Manager合约函数的固定Gas开销
    object Gas {
        val MANAGER_ADD: BigInteger = BigInteger.valueOf(21432L)
        val RIGHT_ADD: BigInteger = BigInteger.valueOf(66014L)
    }

    // Solidity类型关键字
    object SolidityType {
        const val STRING = "string"
        const val ADDRESS = "address"
        const val BOOL = "bool"
        const val UINT32 = "uint32"
        const val UINT64 = "uint64"
    }
}