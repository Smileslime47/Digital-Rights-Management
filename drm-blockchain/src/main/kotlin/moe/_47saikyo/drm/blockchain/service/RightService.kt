package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.annotation.ViewFunction
import moe._47saikyo.drm.blockchain.contract.DRManager.Right
import moe._47saikyo.drm.blockchain.models.ReceiptWrapper
import moe._47saikyo.drm.blockchain.models.RightData
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 版权合约Service接口
 *
 * @author 刘一邦
 */
interface RightService {
    /**
     * 通过版权标题搜索版权合约地址
     *
     * @param title 版权标题
     * @return 版权合约地址列表
     */
    @ViewFunction
    fun searchByTitle(
        callerAddr: String,
        title: String
    ): List<RightData>

    /**
     * 估算部署合约所需的gas
     *
     * @param form 版权部署表单
     * @return 估算的gas
     */
    fun estimateDeploy(
        callerAddr: String,
        form: RightDeployForm
    ): BigInteger

    /**
     * 添加版权合约
     *
     * @param transactionManager 交易管理器
     * @param form 版权部署表单
     */
    fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): ReceiptWrapper<RightData>?

    /**
     * 获取版权合约
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @param index 版权合约索引
     * @return 版权合约
     */
    fun getRight(
        callerAddr: String,
        deployer: String,
        index: Number
    ): RightData

    /**
     * 获取用户的版权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 版权合约列表
     */
    @ViewFunction
    fun getRights(
        callerAddr: String,
        deployer: String
    ): List<RightData>
}