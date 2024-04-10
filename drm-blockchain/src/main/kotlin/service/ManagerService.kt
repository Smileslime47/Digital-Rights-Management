package moe._47saikyo.service

import io.ktor.util.*
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import org.web3j.tx.TransactionManager
import java.math.BigInteger

interface ManagerService {
    fun addRight(
        transactionManager: TransactionManager,
        right: Right
    ):Boolean

    fun addLicense(
        transactionManager: TransactionManager,
        license: License
    ):Boolean

    @ViewFunction
    fun getRights(
        owner: String
    ): List<String>

    @ViewFunction
    fun getLicenses(
        owner: String
    ): List<String>
}