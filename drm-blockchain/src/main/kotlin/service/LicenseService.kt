package moe._47saikyo.service

import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.contract.License
import moe._47saikyo.models.LicenseData
import moe._47saikyo.models.LicenseDeployForm
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
     * @param form 授权部署表单
     * @return 估算的gas
     */
    fun estimateDeploy(form: LicenseDeployForm): BigInteger

    /**
     * 添加授权合约
     *
     * @param transactionManager 交易管理器
     * @param form 授权部署表单
     * @return 授权合约
     */
    fun addLicense(
        transactionManager: TransactionManager,
        form: LicenseDeployForm
    ): License

    /**
     * 获取授权合约的纯数据对象
     *
     * @param licenseAddr 授权合约地址
     * @return 纯数据对象
     */
    @ViewFunction
    fun getPureData(
        licenseAddr: String
    ): LicenseData

    /**
     * 获取用户的授权
     *
     * @param owner 用户地址
     * @return 授权合约列表
     */
    @ViewFunction
    fun getLicenses(
        owner:String
    ): List<LicenseData>
}