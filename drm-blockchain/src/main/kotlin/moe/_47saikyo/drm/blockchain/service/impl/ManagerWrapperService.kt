package moe._47saikyo.drm.blockchain.service.impl

import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.contract.License
import moe._47saikyo.drm.blockchain.contract.Right
import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import moe._47saikyo.drm.blockchain.service.ManagerService
import org.web3j.tx.ReadonlyTransactionManager
import org.web3j.tx.TransactionManager

/**
 * 基于Wrapper的ManagerService实现
 *
 * @author 刘一邦
 */
class ManagerWrapperService : ManagerService {
    override fun searchByTitle(callerAddr: String, title: String): List<String> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.searchByTitle(title).send()?.map { it.toString() } ?: emptyList()
    }

    override fun getRightByRegistrationNumber(callerAddr: String, registrationNumber: String): String {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRightByRegistrationNumber(registrationNumber).send().toString()
    }

    override fun getRightByFileHash(callerAddr: String, fileHash: String): String {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRightByFileHash(fileHash).send().toString()
    }

    override fun canInsertRight(callerAddr: String, rightDeployForm: RightDeployForm): Boolean {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.canInsertRight(
            rightDeployForm.registrationNumber,
            rightDeployForm.fileHash
        ).send()
    }

    override fun addRight(transactionManager: TransactionManager, right: Right) {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        manager.addRight(right.contractAddress).send()
    }

    override fun addLicense(transactionManager: TransactionManager, license: License) {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        manager.addLicense(license.contractAddress).send()
    }

    override fun getRights(owner: String): List<String> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, owner)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRights(owner).send()?.map { it.toString() } ?: emptyList()
    }

    override fun getLicenses(owner: String): List<String> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, owner)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getLicenses(owner).send()?.map { it.toString() } ?: emptyList()
    }
}