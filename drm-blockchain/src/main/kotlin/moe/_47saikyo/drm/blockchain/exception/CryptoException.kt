package moe._47saikyo.drm.blockchain.exception

import moe._47saikyo.drm.core.exception.DrmException

/**
 * 加密异常，可能发生在解密时密钥错误的情况下
 *
 * @param message 异常信息
 */
class CryptoException(override val message:String?): DrmException(message)