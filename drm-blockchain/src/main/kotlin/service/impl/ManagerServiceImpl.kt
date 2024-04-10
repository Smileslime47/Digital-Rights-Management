package moe._47saikyo.service.impl

import moe._47saikyo.BlockChain
import moe._47saikyo.annotation.TxManagerPlaceholder
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.service.ManagerService
import org.web3j.tx.TransactionManager

class ManagerServiceImpl : ManagerService {
    override fun addRight(transactionManager: TransactionManager, right: Right): Boolean {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        return (manager.addRight(right.contractAddress).send() != null)
    }

    override fun addLicense(transactionManager: TransactionManager, license: License): Boolean {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        return (manager.addRight(license.contractAddress).send() != null)
    }

    @ViewFunction
    override fun getRights(owner: String): List<String> {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.getRights(owner).send()?.map { it.toString() } ?: emptyList()
    }

    @ViewFunction
    override fun getLicenses(owner: String): List<String> {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.getLicenses(owner).send()?.map { it.toString() } ?: emptyList()
    }
}
