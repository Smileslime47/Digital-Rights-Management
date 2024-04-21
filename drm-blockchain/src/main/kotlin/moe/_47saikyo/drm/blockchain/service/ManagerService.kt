package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.contract.License
import moe._47saikyo.drm.blockchain.contract.Right
import moe._47saikyo.drm.blockchain.annotation.Internal
import moe._47saikyo.drm.blockchain.annotation.ViewFunction
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import org.web3j.tx.TransactionManager

/**
 * DRManagerService接口
 *
 * @author 刘一邦
 */
@Internal("""
        考虑到CRUD操作和Manager强耦合，不应当也无必要将Manager和Right/License解耦合
        ManagerService的相关操作应当被封装在RightService及LicenseService中
        """)
interface ManagerService {
    //------------------------------以下为搜索的相关操作------------------------------//

    /**
     * 通过版权标题搜索版权合约地址
     *
     * @param title 版权标题
     * @return 版权合约地址列表
     */
    @ViewFunction
    fun searchByTitle(
        callerAddr:String,
        title: String
    ): List<String>

    //------------------------------以下为根据特殊键的相关操作------------------------------//

    /**
     * 通过注册号获取版权合约地址
     *
     * @param registrationNumber 版权注册号
     * @return 版权合约地址
     */
    @ViewFunction
    fun getRightByRegistrationNumber(
        callerAddr:String,
        registrationNumber: String
    ): String

    /**
     * 通过文件哈希获取版权合约地址
     *
     * @param fileHash 文件哈希
     * @return 版权合约地址
     */
    @ViewFunction
    fun getRightByFileHash(
        callerAddr:String,
        fileHash: String
    ): String

    /**
     * 判断是否可以插入版权
     *
     * @param rightDeployForm 版权部署表单
     * @return 是否可以插入
     */
    @ViewFunction
    fun canInsertRight(
        callerAddr:String,
        rightDeployForm: RightDeployForm
    ): Boolean

    //------------------------------以下为以地址为基础的CRUD操作------------------------------//

    /**
     * 添加版权合约
     *
     * @param transactionManager 交易管理器
     * @param right 版权合约
     * @return 是否添加成功
     */
    fun addRight(
        transactionManager: TransactionManager,
        right: Right
    )

    /**
     * 添加授权合约
     *
     * @param transactionManager 交易管理器
     * @param license 授权合约
     * @return 是否添加成功
     */
    fun addLicense(
        transactionManager: TransactionManager,
        license: License
    )

    /**
     * 获取用户的版权
     *
     * @param owner 用户地址
     * @return 版权合约地址列表
     */
    @ViewFunction
    fun getRights(
        owner: String
    ): List<String>

    /**
     * 获取用户的授权
     *
     * @param owner 用户地址
     * @return 授权合约地址列表
     */
    @ViewFunction
    fun getLicenses(
        owner: String
    ): List<String>
}