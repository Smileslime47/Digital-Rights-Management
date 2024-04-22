package moe._47saikyo.drm.blockchain.service

import moe._47saikyo.drm.blockchain.annotation.Internal
import moe._47saikyo.drm.blockchain.annotation.ViewFunction
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.contract.DRManager.*
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * DRManagerService接口
 *
 * @author 刘一邦
 */
@Internal(
    """
        考虑到CRUD操作和Manager强耦合，不应当也无必要将Manager和Right/License解耦合
        ManagerService的相关操作应当被封装在RightService及LicenseService中
        """
)
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
        callerAddr: String,
        title: String
    ): List<Right>

    //------------------------------以下为根据特殊键的相关操作------------------------------//

    /**
     * 通过注册号获取版权合约地址
     *
     * @param registrationNumber 版权注册号
     * @return 版权合约地址
     */
    @ViewFunction
    fun getRightByRegistrationNumber(
        callerAddr: String,
        registrationNumber: String
    ): Right

    /**
     * 通过文件哈希获取版权合约地址
     *
     * @param fileHash 文件哈希
     * @return 版权合约地址
     */
    @ViewFunction
    fun getRightByFileHash(
        callerAddr: String,
        fileHash: String
    ): Right

    /**
     * 判断是否可以插入版权
     *
     * @param registrationNumber 版权注册号
     * @param fileHash 文件哈希
     * @return 是否可以插入
     */
    @ViewFunction
    fun canInsertRight(
        callerAddr: String,
        registrationNumber: String,
        fileHash: String
    ): Boolean

    //------------------------------以下为以地址为基础的CRUD操作------------------------------//

    /**
     * 添加版权合约
     *
     * @param transactionManager 交易管理器
     * @param right 版权合约
     * @return 交易收据
     */
    fun addRight(
        transactionManager: TransactionManager,
        right: Right
    ): TransactionReceipt

    /**
     * 添加授权合约
     *
     * @param transactionManager 交易管理器
     * @param license 授权合约
     * @return 交易收据
     */
    fun addLicense(
        transactionManager: TransactionManager,
        license: License
    ): TransactionReceipt

    /**
     * 获取版权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @param index 版权索引
     * @return 版权
     */
    @ViewFunction
    fun getRight(
        callerAddr: String,
        deployer: String,
        index: Number
    ): Right

    /**
     * 获取用户的版权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 版权合约地址列表
     */
    @ViewFunction
    fun getRights(
        callerAddr: String,
        deployer: String
    ): List<Right>

    /**
     * 获取最后一个版权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 版权
     */
    @ViewFunction
    fun getLastRight(
        callerAddr: String,
        deployer: String
    ): Right

    /**
     * 获取授权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @param index 授权索引
     * @return 授权
     */
    @ViewFunction
    fun getLicense(
        callerAddr: String,
        deployer: String,
        index: Number
    ): License

    /**
     * 获取用户的授权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 授权合约地址列表
     */
    @ViewFunction
    fun getLicenses(
        callerAddr: String,
        deployer: String
    ): List<License>

    /**
     * 获取最后一个授权
     *
     * @param callerAddr 调用者地址
     * @param deployer 部署者地址
     * @return 授权
     */
    @ViewFunction
    fun getLastLicense(
        callerAddr: String,
        deployer: String
    ): License
}