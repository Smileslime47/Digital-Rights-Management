package moe._47saikyo.drm.blockchain

import java.math.BigDecimal
import java.math.BigInteger

/**
 * 以太坊货币转换器,用于以太坊货币单位之间的转换
 *
 * @author 刘一邦
 */
object CurrencyConverter {
    // 以太币和wei的转换因子
    private const val ETHER_WEI_FACTOR = 1e18.toLong()

    /**
     * 将wei转换为以太币
     *
     * @param wei wei
     * @return 以太币
     */
    fun weiToEther(wei: BigInteger): BigDecimal {
        return weiToEther(wei.toBigDecimal())
    }

    /**
     * 将wei转换为以太币
     *
     * @param wei wei
     * @return 以太币
     */
    fun weiToEther(wei: BigDecimal): BigDecimal {
        return wei.divide(BigDecimal(ETHER_WEI_FACTOR))
    }

    /**
     * 将以太币转换为wei
     *
     * @param ether 以太币
     * @return wei
     */
    fun etherToWei(ether: BigDecimal): BigDecimal {
        return ether.multiply(BigDecimal(ETHER_WEI_FACTOR))
    }
}