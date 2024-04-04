package moe._47saikyo.service

import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightData
import moe._47saikyo.models.RightDeployForm
import org.web3j.crypto.Credentials
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 版权合约Service接口
 *
 * @author 刘一邦
 */
interface RightService {
    /**
     * 估算部署合约所需的gas
     *
     * @param form 版权部署表单
     * @return 估算的gas
     */
    fun estimate(form: RightDeployForm): BigInteger

    /**
     * 添加版权合约
     *
     * @param transactionManager 交易管理器
     * @param form 版权部署表单
     * @return 版权合约
     */
    fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): Right

    /**
     * 获取版权合约
     *
     * @param rightAddr 版权合约地址
     * @return 版权合约
     */
    fun getRight(rightAddr: String): RightData

    /**
     * 获取用户的版权
     *
     * @param owner 用户地址
     * @return 版权合约列表
     */
    fun getRights(owner: String): List<RightData>
}