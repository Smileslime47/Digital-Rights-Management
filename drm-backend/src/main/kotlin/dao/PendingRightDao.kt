package moe._47saikyo.dao

import moe._47saikyo.domain.PendingRight
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder

/**
 * NoticeDao接口
 *
 * @author 刘一邦
 */
interface PendingRightDao {
    /**
     * 获取待审核版权
     * @param where 查询条件
     * @return 待审核版权
     */
    suspend fun getPendingRight(where: SqlExpressionBuilder.() -> Op<Boolean>): PendingRight?

    /**
     * 获取*所有*待审核版权对象
     * @return 所有待审核版权
     */
    suspend fun getPendingRights(): List<PendingRight>

    /**
     * 根据条件获取待审核版权对象
     * @param where 查询条件
     * @return 待审核版权对象
     */
    suspend fun getPendingRights(where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingRight>

    /**
     * 根据条件计算待审核版权数量
     * @param where 查询条件
     * @return 待审核版权数量
     */
    suspend fun countPendingRights(where: SqlExpressionBuilder.() -> Op<Boolean>): Long

    /**
     * 根据条件分页获取待审核版权
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @param where 查询条件
     * @return 待审核版权
     */
    suspend fun getPendingRights(pageSize: Int, pageNumber: Int, where: SqlExpressionBuilder.() -> Op<Boolean>): List<PendingRight>

    /**
     * 插入待审核版权
     * @param pendingRight 待审核版权
     * @return 待审核版权
     */
    suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight?

    /**
     * 更新待审核版权
     * @param pendingRight 待审核版权
     * @return 是否更新成功
     */
    suspend fun updatePendingRight(pendingRight: PendingRight): Boolean

    /**
     * 删除待审核版权
     * @param pendingRight 待审核版权
     * @return 是否删除成功
     */
    suspend fun deletePendingRight(pendingRight: PendingRight): Boolean
}