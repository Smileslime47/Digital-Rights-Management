package moe._47saikyo.drm.backend.service.impl

import moe._47saikyo.drm.backend.dao.ReceiptDao
import moe._47saikyo.drm.backend.mapper.ReceiptTable
import moe._47saikyo.drm.backend.service.ReceiptService
import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.core.domain.Receipt
import org.jetbrains.exposed.sql.and
import org.koin.java.KoinJavaComponent.inject

class ReceiptServiceImpl : ReceiptService {
    private val receiptDao: ReceiptDao by inject(ReceiptDao::class.java)
    override suspend fun getReceiptByKeyPair(keyPair: KeyPairData): Receipt? =
        receiptDao.getReceipt {
            (ReceiptTable.deployer eq keyPair.deployer) and (ReceiptTable.deploy_index eq keyPair.arrayIndex)
        }


    override suspend fun insertReceipt(receipt: Receipt): Receipt? =
        receiptDao.insertReceipt(receipt)
}