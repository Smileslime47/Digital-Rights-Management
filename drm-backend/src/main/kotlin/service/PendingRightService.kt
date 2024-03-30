package moe._47saikyo.service

import domain.PendingRight
import moe._47saikyo.contract.Right
import org.web3j.tx.TransactionManager

interface PendingRightService {
    suspend fun getPendingRight(id: Long): PendingRight?
    suspend fun getPendingRights(): List<PendingRight>
    suspend fun getPendingRights(address:String): List<PendingRight>
    suspend fun insertPendingRight(pendingRight: PendingRight): PendingRight?
    suspend fun confirmPendingRight(id: Long): Boolean
    suspend fun deployPendingRight(id: Long,transactionManager: TransactionManager): Right?
    suspend fun rejectPendingRight(id: Long): Boolean
}