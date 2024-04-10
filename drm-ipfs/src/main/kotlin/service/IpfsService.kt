package moe._47saikyo.service

import moe._47saikyo.exception.IpfsNotConnectedException

import io.ipfs.api.IPFS
import kotlin.jvm.Throws

/**
 * IPFS服务接口
 *
 * @author 刘一邦
 */
interface IpfsService {
    /**
     * 上传文件
     *
     * @param content 文件内容
     * @return 文件hash
     */
    fun upload(content: ByteArray): String

    /**
     * 下载文件
     *
     * @param hash 文件hash
     * @return 文件内容
     */
    fun download(hash: String): ByteArray

    companion object {
        /**
         * IPFS地址
         */
        var ipfsAddress: String = ""
            private set

        /**
         * 是否已初始化
         */
        var initialized: Boolean = false
            private set

        /**
         * 初始化
         *
         * @param ipfsAddress IPFS地址
         * @see [IpfsService.ipfsAddress]
         */
        fun init(ipfsAddress: String) {
            Companion.ipfsAddress = ipfsAddress
            initialized = true
        }

        /**
         * 获取IPFS实例
         *
         * @return IPFS实例
         */
        @Throws(IpfsNotConnectedException::class)
        fun getInstance(): IPFS {
            if (!initialized) {
                throw IpfsNotConnectedException("IPFS service is not initialized")
            }

            return IPFS(ipfsAddress)
        }
    }
}