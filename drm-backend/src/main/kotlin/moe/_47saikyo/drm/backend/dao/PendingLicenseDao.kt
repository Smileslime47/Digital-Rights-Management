package moe._47saikyo.drm.backend.dao

import moe._47saikyo.drm.core.domain.PendingLicense
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

/**
 * PendingLicenseDao接口
 *
 * @author 刘一邦
 */
interface PendingLicenseDao {
    /**
     * 获取待审核授权
     *
     * @param where 查询条件
     * @return 待审核授权
     */
    suspend fun getPendingLicense(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingLicense?

    /**
     * 获取*所有*待审核授权对象
     *
     * @return 所有待审核授权
     */
    suspend fun getPendingLicenses(): List<PendingLicense>

    /**
     * 根据条件获取待审核授权对象
     *
     * @param where 查询条件
     * @return 待审核授权对象
     */
    suspend fun getPendingLicenses(where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingLicense>

    /**
     * 根据条件计算待审核授权数量
     *
     * @param where 查询条件
     * @return 待审核授权数量
     */
    suspend fun countPendingLicenses(where: SqlExpressionBuilder.() -> Op<Boolean>): Long

    /**
     * 根据条件分页获取待审核授权
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param where 查询条件
     * @return 待审核授权
     */
    suspend fun getPendingLicenses(pageSize: Int, pageNumber: Int, where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingLicense>

    /**
     * 插入待审核授权
     *
     * @param pendingLicense 待审核授权
     * @return 待审核授权
     */
    suspend fun insertPendingLicense(pendingLicense: PendingLicense): PendingLicense?

    /**
     * 更新待审核授权
     *
     * @param pendingLicense 待审核授权
     * @return 是否更新成功
     */
    suspend fun updatePendingLicense(pendingLicense: PendingLicense): Boolean

    /**
     * 删除待审核授权
     *
     * @param pendingLicense 待审核授权
     * @return 是否删除成功
     */
    suspend fun deletePendingLicense(pendingLicense: PendingLicense): Boolean
}