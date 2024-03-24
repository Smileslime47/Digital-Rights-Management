package moe._47saikyo.service.impl

import moe._47saikyo.BlockChain
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.RightService
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.DefaultGasProvider

class RightServiceImpl : RightService {
    override fun addRight(transactionManager: TransactionManager, form: RightDeployForm): Right =
        Right.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            DefaultGasProvider(),
            form.title,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description
        ).send()

    override fun getRight(transactionManager: TransactionManager, rightAddr: String): Right =
        Right.load(rightAddr, BlockChain.web3jInstance, transactionManager, DefaultGasProvider())
}