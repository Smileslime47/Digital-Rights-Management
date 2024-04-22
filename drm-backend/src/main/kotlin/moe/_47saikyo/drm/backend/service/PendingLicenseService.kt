package moe._47saikyo.drm.backend.service

import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.blockchain.models.LicenseData
import moe._47saikyo.drm.blockchain.models.LicenseDeployForm
import moe._47saikyo.drm.core.domain.PendingLicense
import org.web3j.tx.TransactionManager

/**
 * Pending License Service
 *
 * @author 刘一邦
 */
interface PendingLicenseService {
    /**
     * 将待审核授权转换为部署表单
     *
     * @param pendingLicense 待审核授权
     * @return 部署表单
     */
    fun convertToDeployForm(pendingLicense: PendingLicense): LicenseDeployForm

    /**
     * 根据ID获取待审核授权
     *
     * @param id 待审核授权ID
     * @return 待审核授权
     */
    suspend fun getPendingLicense(id: Long): PendingLicense?

    /**
     * 计算某版权下的待审核授权数量
     *
     * @param rightKey 在合约中唯一定位版权的键对
     * @return 待审核授权数量
     */
    suspend fun countPendingLicensesOfRight(rightKey: KeyPairData): Long

    /**
     * 获取某版权下某用户拥有的的待审核授权
     *
     * @param rightKey 在合约中唯一定位版权的键对
     * @param licenseDeployerAddr 授权部署者地址
     * @return 待审核授权数量
     */
    suspend fun countPendingLicensesOfRight(rightKey: KeyPairData, licenseDeployerAddr: String): Long


    /**
     * 获取某版权下的待审核授权
     *
     * @param rightKey 在合约中唯一定位版权的键对
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @return 待审核授权
     */
    suspend fun getPendingLicensesOfRight(rightKey: KeyPairData, pageSize: Int, pageNumber: Int): List<PendingLicense>

    /**
     * 获取某版权下某用户拥有的的待审核授权
     *
     * @param rightKey 在合约中唯一定位版权的键对
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param licenseDeployerAddr 授权部署者地址
     * @return 待审核授权数量
     */
    suspend fun getPendingLicenseOfRight(rightKey: KeyPairData, licenseDeployerAddr: String, pageSize: Int, pageNumber: Int): List<PendingLicense>

    /**
     * 获取用户的待审核授权数量
     *
     * @param address 用户地址
     * @return 待审核授权数量
     */
    suspend fun countPendingLicenseOfUser(address: String): Long

    /**
     * 获取用户的待审核授权
     *
     * @param address 用户地址
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @return 待审核授权
     */
    suspend fun getPendingLicensesOfUser(address: String, pageSize: Int, pageNumber: Int): List<PendingLicense>

    /**
     * 插入待审核授权
     *
     * @param pendingLicense 待审核授权
     * @return 待审核授权
     */
    suspend fun insertPendingLicense(pendingLicense: PendingLicense): PendingLicense?

    /**
     * 认证待审核授权，将状态从*PENDING*改为*CONFIRMED*
     *
     * @param id 待审核授权ID
     * @param estimatePrice 估价
     * @return 是否认证成功
     */
    suspend fun confirmPendingLicense(id: Long, estimatePrice: Long): Boolean

    /**
     * 部署待审核授权，将状态从*PENDING*改为*DEPLOYED*
     *
     * @param id 待审核授权ID
     * @param transactionManager 交易管理器
     * @return 部署后的授权
     */
    suspend fun deployPendingLicense(id: Long, transactionManager: TransactionManager): LicenseData?

    /**
     * 拒绝待审核授权，将状态从*PENDING*改为*REJECTED*
     *
     * @param id 待审核授权ID
     * @param rejectReason 拒绝原因
     * @return 是否拒绝成功
     */
    suspend fun rejectPendingLicense(id: Long, rejectReason: String): Boolean
}