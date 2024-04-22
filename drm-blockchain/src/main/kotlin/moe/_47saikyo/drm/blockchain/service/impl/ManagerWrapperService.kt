package moe._47saikyo.drm.blockchain.service.impl

import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.contract.DRManager
import moe._47saikyo.drm.blockchain.contract.DRManager.License
import moe._47saikyo.drm.blockchain.contract.DRManager.Right
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.string
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tx.ReadonlyTransactionManager
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 基于Wrapper的ManagerService实现
 *
 * @author 刘一邦
 */
class ManagerWrapperService : ManagerService {
    override fun searchByTitle(callerAddr: String, title: String): List<Right> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.searchByTitle(title).send() as List<Right>
    }

    override fun getRightByRegistrationNumber(callerAddr: String, registrationNumber: String): Right {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRightByRegistrationNumber(registrationNumber).send()
    }

    override fun getRightByFileHash(callerAddr: String, fileHash: String): Right {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRightByFileHash(fileHash).send()
    }

    override fun canInsertRight(callerAddr: String, registrationNumber: String, fileHash: String): Boolean {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.canInsertRight(registrationNumber, fileHash).send()
    }

    override fun addRight(transactionManager: TransactionManager, right: Right): TransactionReceipt {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        return manager.addRight(right).send()
    }

    override fun addLicense(transactionManager: TransactionManager, license: License): TransactionReceipt {
        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        return manager.addLicense(license).send()
    }

    override fun getRight(callerAddr: String, deployer: String, index: Number): Right {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRight(deployer, BigInteger.valueOf(index.toLong())).send()
    }

    override fun getRights(callerAddr: String, deployer: String): List<Right> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getRights(deployer).send() as List<Right>
    }

    override fun getLastRight(callerAddr: String, deployer: String): Right {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getLastRight(deployer).send()
    }

    override fun getLicense(callerAddr: String, deployer: String, index: Number): License {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getLicense(deployer, BigInteger.valueOf(index.toLong())).send()
    }

    override fun getLicenses(callerAddr: String, deployer: String): List<License> {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getLicenses(deployer).send() as List<License>
    }

    override fun getLastLicense(callerAddr: String, deployer: String): License {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, deployer)

        val manager = DRManager.load(
            BlockChain.managerAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )

        return manager.getLastLicense(deployer).send()
    }
}