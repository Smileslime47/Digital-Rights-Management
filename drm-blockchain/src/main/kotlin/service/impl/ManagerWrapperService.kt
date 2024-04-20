package moe._47saikyo.service.impl

import moe._47saikyo.BlockChain
import moe._47saikyo.annotation.TxManagerPlaceholder
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.ManagerService
import org.web3j.tx.TransactionManager

/**
 * 基于Wrapper的ManagerService实现
 *
 * @author 刘一邦
 */
@Deprecated("Use ManagerServiceImpl instead,maintenance only.")
class ManagerWrapperService:ManagerService {
    override fun searchByTitle(callerAddr: String, title: String): List<String> {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.searchByTitle(title).send()?.map { it.toString() } ?: emptyList()
    }

    override fun getRightByRegistrationNumber(callerAddr: String, registrationNumber: String): String {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.getRightByRegistrationNumber(registrationNumber).send().toString()
    }

    override fun getRightByFileHash(callerAddr: String, fileHash: String): String {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.getRightByFileHash(fileHash).send().toString()
    }

    override fun canInsertRight(callerAddr: String, rightDeployForm: RightDeployForm): Boolean {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            @TxManagerPlaceholder
            BlockChain.bankTxManager,
            BlockChain.gasProvider
        )

        return manager.canInsertRight(
            rightDeployForm.registrationNumber,
            rightDeployForm.fileHash
        ).send()
    }

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