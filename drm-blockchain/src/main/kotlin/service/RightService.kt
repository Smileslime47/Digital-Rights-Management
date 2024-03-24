package moe._47saikyo.service

import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightDeployForm
import org.web3j.tx.TransactionManager
import java.math.BigInteger

interface RightService {
    fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): Right

    fun getRight(transactionManager: TransactionManager, rightAddr: String): Right
}