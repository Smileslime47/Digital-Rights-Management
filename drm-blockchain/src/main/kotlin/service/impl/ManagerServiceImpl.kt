package moe._47saikyo.service.impl

import moe._47saikyo.BlockChain
import moe._47saikyo.address
import moe._47saikyo.annotation.TxManagerPlaceholder
import moe._47saikyo.annotation.ViewFunction
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.License
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.ManagerService
import moe._47saikyo.string
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.tx.TransactionManager

class ManagerServiceImpl : ManagerService {
    private val logger = org.slf4j.LoggerFactory.getLogger(ManagerServiceImpl::class.java)

    @ViewFunction
    override fun searchByTitle(
        callerAddr: String,
        title: String
    ): List<String> {
        val function = Function("searchByTitle", listOf(string(title)), listOf(TypeReference.makeTypeReference("address[]")))

        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val list = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as List<Address>

        return list.map { it.toString() }
    }

    @ViewFunction
    override fun getRightByRegistrationNumber(
        callerAddr: String,
        registrationNumber: String
    ): String {
        val function = Function("getRightByRegistrationNumber", listOf(string(registrationNumber)), listOf(TypeReference.makeTypeReference("address")))

        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val right = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        return right
    }

    @ViewFunction
    override fun getRightByFileHash(
        callerAddr: String,
        fileHash: String
    ): String {
        val function = Function("getRightByFileHash", listOf(string(fileHash)), listOf(TypeReference.makeTypeReference("address")))

        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val right = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as String

        return right
    }

    @ViewFunction
    override fun canInsertRight(
        callerAddr: String,
        rightDeployForm: RightDeployForm
    ): Boolean {
        val function = Function("canInsertRight",
            listOf(
                string(rightDeployForm.registrationNumber),
                string(rightDeployForm.fileHash)
            ),
            listOf(TypeReference.makeTypeReference("bool")))

        val transaction = Transaction.createEthCallTransaction(
            callerAddr,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val canInsert = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as Boolean

        return canInsert
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

    @ViewFunction
    override fun getRights(owner: String): List<String> {
        val function = Function("getRights", listOf(address(owner)), listOf(TypeReference.makeTypeReference("address[]")))

        val transaction = Transaction.createEthCallTransaction(
            owner,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val list = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as List<Address>

        return list.map { it.toString() }
    }

    @ViewFunction
    override fun getLicenses(owner: String): List<String> {
        val function = Function("getLicenses", listOf(address(owner)), listOf(TypeReference.makeTypeReference("address[]")))

        val transaction = Transaction.createEthCallTransaction(
            owner,
            BlockChain.managerAddr,
            FunctionEncoder.encode(function)
        )

        //发送交易并获取结果
        val result = BlockChain.web3jInstance!!.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get().value

        //获取函数返回值
        val list = FunctionReturnDecoder.decode(result, function.outputParameters)[0].value as List<Address>

        return list.map { it.toString() }
    }
}
