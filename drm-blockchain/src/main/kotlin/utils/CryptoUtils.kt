package moe._47saikyo.utils

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 * 加密工具类
 *
 * @author 刘一邦
 */
object CryptoUtils {
    //随机数生成类
    private val random = SecureRandom()

    /**
     * 获取一个随机的128位初始化向量
     *
     * @return 由SecureRandom类生成的128位随机数组
     */
    fun getRandomIv(): ByteArray {
        val iv = ByteArray(16)
        random.nextBytes(iv)
        return iv
    }

    /**
     * 对指定数据采用AES-128算法加密
     *
     * @param data 明文数据
     * @param secretKey 密钥
     * @param iv 见[CryptoUtils.getRandomIv]
     * @return 密文数据
     */
    fun encrypt(data: ByteArray, secretKey: SecretKey, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        return cipher.doFinal(data)
    }

    /**
     * 对指定数据采用AES-128算法加密
     *
     * @param data 密文数据
     * @param secretKey 密钥
     * @param iv 见[CryptoUtils.getRandomIv]
     * @return 明文数据
     */
    fun decrypt(data: ByteArray, secretKey: SecretKey, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        return cipher.doFinal(data)
    }
}