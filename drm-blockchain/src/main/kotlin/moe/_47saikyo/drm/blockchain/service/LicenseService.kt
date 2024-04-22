package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.annotation.ViewFunction
import moe._47saikyo.drm.blockchain.models.LicenseData
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import moe._47saikyo.drm.blockchain.models.ReceiptWrapper
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 授权合约Service接口
 *
 * @author 刘一邦
 */
interface LicenseService {
    /**
     * 估算部署合约所需的gas
     *
     * @param callerAddr 调用者地址
     * @param form 授权部署表单
     * @return 估算的gas
     */
    fun estimateDeploy(
        callerAddr: String,
        form: LicenseDeployForm
    ): BigInteger

    /**
     * 添加授权合约
     *
     * @param transactionManager 交易管理器
     * @param form 授权部署表单
     */
    fun addLicense(
        transactionManager: TransactionManager,
        form: LicenseDeployForm
    ): ReceiptWrapper<LicenseData>?

    /**
     * 获取授权合约
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @param index 授权合约索引
     * @return 授权合约
     */
    fun getLicense(
        callerAddr: String,
        deployer: String,
        index: Number
    ): LicenseData

    /**
     * 获取用户的授权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 授权合约列表
     */
    @ViewFunction
    fun getLicenses(
        callerAddr: String,
        deployer: String
    ): List<LicenseData>
}