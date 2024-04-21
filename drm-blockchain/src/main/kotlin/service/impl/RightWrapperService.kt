package moe._47saikyo.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import moe._47saikyo.BlockChain
import moe._47saikyo.Estimate
import moe._47saikyo.constant.BlockChainConstant
import moe._47saikyo.contract.Right
import moe._47saikyo.models.RightData
import moe._47saikyo.models.RightDeployForm
import moe._47saikyo.service.ManagerService
import moe._47saikyo.service.RightService
import moe._47saikyo.string
import moe._47saikyo.uint64
import org.koin.java.KoinJavaComponent
import org.web3j.abi.FunctionEncoder
import org.web3j.tx.ReadonlyTransactionManager
import org.web3j.tx.TransactionManager
import java.math.BigInteger

/**
 * 基于Wrapper的RightService实现
 *
 * @author 刘一邦
 */
class RightWrapperService : RightService {
    private val managerService: ManagerService by KoinJavaComponent.inject(ManagerService::class.java)
    private val logger = org.slf4j.LoggerFactory.getLogger(RightEthCallService::class.java)

    override fun searchByTitle(
        callerAddr: String,
        title: String
    ): List<String> =
        managerService.searchByTitle(callerAddr, title)

    override fun estimateDeploy(
        callerAddr: String,
        form: RightDeployForm
    ): BigInteger {
        val binCode = Right.BINARY

        val encodedConstructor = FunctionEncoder.encodeConstructor(
            listOf(
                string(form.title),
                string(form.owner),
                string(form.registrationNumber),
                uint64(form.issueTime),
                uint64(form.expireTime),
                string(form.description),
                string(form.fileName),
                string(form.fileHash)
            )
        )

        return Estimate.estimateDeploy(callerAddr, "$binCode$encodedConstructor")
            .add(BlockChainConstant.Gas.MANAGER_ADD)
    }

    override fun addRight(
        transactionManager: TransactionManager,
        form: RightDeployForm
    ): Right {
        val right = Right.deploy(
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider,
            form.title,
            form.owner,
            form.registrationNumber,
            form.issueTime,
            form.expireTime,
            form.description,
            form.fileName,
            form.fileHash
        ).send()

        managerService.addRight(transactionManager, right)

        return right
    }

    override fun addLicense(
        transactionManager: TransactionManager,
        rightAddr: String,
        licenseAddr: String
    ) {
        val right = Right.load(
            rightAddr,
            BlockChain.web3jInstance,
            transactionManager,
            BlockChain.gasProvider
        )

        right.addLicense(licenseAddr).send()
    }

    override fun getPureData(
        callerAddr: String,
        rightAddr: String
    ): RightData {
        val txManager = ReadonlyTransactionManager(BlockChain.web3jInstance, callerAddr)

        logger.info("getPureData[$rightAddr]")
        val right = Right.load(
            rightAddr,
            BlockChain.web3jInstance,
            txManager,
            BlockChain.gasProvider
        )
        val json = right.serialize().send()
        logger.info("getPureData[$json]")
        return jacksonObjectMapper().readerFor(RightData::class.java).readValue(json)
    }

    override fun getRights(owner: String): List<RightData> {
        val addrs = managerService.getRights(owner)
        logger.info("getRights[$addrs]")
        return addrs.map { getPureData(owner, it) }
    }
}