package moe._47saikyo.service

import moe._47saikyo.contract.Right
import moe._47saikyo.domain.PendingRight
import moe._47saikyo.models.RightDeployForm
import org.web3j.tx.TransactionManager

/**
 * PendingRightService接口
 *
 * @author 刘一邦

 */
interface PendingRightService {
    /**
     * 将待审核版权转换为部署表单
     *
     * @param pendingRight 待审核版权
     * @return 部署表单
     */
    fun convertToDeployForm(pendingRight: PendingRight):RightDeployForm

    /**
     * 根据ID获取待审核版权
     *
     * @param id 待审核版权ID
     * @return 待审核版权
     */
    suspend fun getPendingRight(id: Long): PendingRight?

    /**
     * 计算状态为*PENDING*的待审核版权数量
     *
     * @return 待审核版权数量
     */
    suspend fun countPendingRights(): Long

    /**
     * 获取状态为*PENDING*的待审核版权
     *
     * @param pageSize 页大小
     * @param pageNumber 页码
     * @return 待审核版权
     */
    suspend fun getPendingRights(pageSize: Int, pageNumber: Int): List<PendingRight>

    /**
     * 获取用户的待审核版权数量
     *
     * @param address 用户地址
     * @return 待审核版权数量
     */
    suspend fun countPendingRights(address: String): Long

    /**
     * 获取用户的待审核版权
     *
     * @param address 用户地址
     * @return 待审核版权
     */
    suspend fun getPendingRights(pageSize: Int, pageNumber: Int, address: String): List<PendingRight>

    /**
     * 插入待审核版权
     *
     * @param pendingRight 待审核版权
     * @return 待审核版权
     */
    suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight?

    /**
     * 认证待审核版权，将状态从*PENDING*改为*CONFIRMED*
     *
     * @param id 待审核版权ID
     * @param estimatePrice 估价
     * @return 是否确认成功
     */
    suspend fun confirmPendingRight(id: Long, estimatePrice: Long): Boolean


    /**
     * 部署待审核版权，将状态从*ESTIMATED*改为*DEPLOYED*
     *
     * @param id 待审核版权ID
     * @param transactionManager 交易管理器
     * @return 已部署版权
     */
    suspend fun deployPendingRight(id: Long, transactionManager: TransactionManager): Right?

    /**
     * 拒绝待审核版权，将状态从*PENDING*改为*REJECTED*
     *
     * @param id 待审核版权ID
     * @param rejectReason 拒绝原因
     * @return 是否拒绝成功
     */
    suspend fun rejectPendingRight(id: Long,rejectReason:String): Boolean
}