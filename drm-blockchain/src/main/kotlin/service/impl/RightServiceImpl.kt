package moe._47saikyo.service.impl

import moe._47saikyo.*
import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.RightService
import org.web3j.abi.FunctionEncoder
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

class RightServiceImpl : RightService {
    override fun estimate(from: String, form: RightDeployForm): BigInteger {
        val binCode = Right.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string(form.title),
                string(form.registrationNumber),
                uint64(form.issueTime),
                uint64(form.expireTime),
                string(form.description)
            )
        )

        return Estimate.estimateDeploy("$binCode$encodedConstructor")
    }

    override fun addRight(transactionManager: TransactionManager, form: RightDeployForm): Right {
        val right = Right.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            DefaultGasProvider(),
            form.title,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description
        ).send()

        val manager = DRManager.load(BlockChain.managerAddr, BlockChain.web3jInstance, transactionManager, BlockChain.gasProvider)

        manager.addRight(right.contractAddress).send()

        return right
    }

    override fun getRight(transactionManager: TransactionManager, rightAddr: String): Right =
        Right.load(rightAddr, BlockChain.web3jInstance, transactionManager, BlockChain.gasProvider)

    override fun getRights(owner: String): List<Right> {
//        val manager = BlockChain.managerByBank
//        val rights = manager?.getRights(owner)?.send()
//        return rights!!.toList()
        TODO()
    }
}