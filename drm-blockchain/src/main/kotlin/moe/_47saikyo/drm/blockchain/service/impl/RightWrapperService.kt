package moe._47saikyo.drm.blockchain.service.impl

import moe._47saikyo.drm.blockchain.contract.DRManager.Right
import moe._47saikyo.drm.blockchain.models.ReceiptWrapper
import moe._47saikyo.drm.blockchain.models.RightData
import moe._47saikyo.drm.blockchain.models.RightDeployForm
import moe._47saikyo.drm.blockchain.service.ManagerService
import moe._47saikyo.drm.blockchain.service.RightService
import org.koin.java.KoinJavaComponent
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 基于Wrapper的RightService实现
 *
 * @author 刘一邦
 */
class RightWrapperService : RightService {
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)
    private val logger = org.slf4j.LoggerFactory.getLogger(RightWrapperService::class.java)

    override fun searchByTitle(
        callerAddr: String,
        title: String
    ): List<RightData> =
        managerService.searchByTitle(callerAddr, title).map { RightData.fromRightStruct(it) }

    override fun estimateDeploy(
        callerAddr: String,
        form: RightDeployForm
    ): BigInteger {
//        //TODO
//        val binCode = Right.BINARY
//
//        val encodedConstructor = FunctionEncoder.encodeConstructor(
//            listOf(
//                string(form.title),
//                string(form.owner),
//                string(form.registrationNumber),
//                uint64(form.issueTime),
//                uint64(form.expireTime),
//                string(form.description),
//                string(form.fileName),
//                string(form.fileHash)
//            )
//        )
//
//        return Estimate.estimateCall(callerAddr, "$binCode$encodedConstructor")
//            .add(BlockChainConstant.Gas.MANAGER_ADD)

        return BigInteger.valueOf(0)
    }

    override fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): ReceiptWrapper<RightData>? {
        val right = Right(
            BigInteger.valueOf(0),
            form.title,
            transactionManager.fromAddress,
            form.owner,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description,
            form.fileName,
            form.fileHash,
            emptyList()
        )

        val receipt = managerService.addRight(transactionManager, right)

        val lastRight =
            RightData.fromRightStruct(
                managerService.getLastRight(transactionManager.fromAddress, transactionManager.fromAddress)
            )

        return if (lastRight == RightData.fromRightStruct(right)) {
            ReceiptWrapper(receipt, lastRight)
        } else {
            null
        }
    }

    override fun getRight(callerAddr: String, deployer: String, index: Number): RightData {
        val right = managerService.getRight(callerAddr, deployer, index)
        logger.info("getRight[$right]")
        return RightData.fromRightStruct(right)
    }

    override fun getRights(callerAddr: String, deployer: String): List<RightData> {
        val rights = managerService.getRights(callerAddr, deployer)
        logger.info("getRights[$rights]")
        return rights.map { RightData.fromRightStruct(it) }
    }
}