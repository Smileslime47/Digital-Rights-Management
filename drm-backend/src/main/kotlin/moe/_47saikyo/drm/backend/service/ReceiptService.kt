package moe._47saikyo.drm.backend.service

import moe._47saikyo.drm.blockchain.models.KeyPairData
import moe._47saikyo.drm.core.domain.Receipt

interface ReceiptService {
    suspend fun getReceiptByKeyPair(keyPair: KeyPairData): Receipt?
    suspend fun insertReceipt(receipt: Receipt): Receipt?
}