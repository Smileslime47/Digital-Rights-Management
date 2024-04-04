package moe._47saikyo.utils

import moe._47saikyo.exception.CryptoException
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


/**
 * 加密工具类
 *
 * @author 刘一邦
 */
object CryptoUtils {
    //128比特初始化向量
    const val IV_128_BIT = 16

    //随机数生成类
    private val random = SecureRandom()

    //硬编码盐值
    private val salt = arrayOf(0xaf, 0xec, 0x8f, 0xfd, 0x00, 0x57, 0xdd, 0x20, 0x6f, 0xae, 0x2d, 0x59, 0x40, 0xb6, 0x6b, 0x25).map { it.toByte() }.toByteArray()

    /**
     * 获取一个随机的初始化向量
     *
     * @param len 字节长度，16则对应128位
     * @return 由SecureRandom类生成的128位随机数组
     */
    fun getRandomByteArray(len: Int): ByteArray {
        val arr = ByteArray(len)

        return random.nextBytes(arr).let { arr }
    }

    /**
     * 通过PBKDF2算法将密码短语转换为256比特长度的密钥
     * 为了保证传入相同的密码能生成相同的密钥，所以这里的盐值是固定的
     *
     * @param phrase 密码短语
     * @return SecretKey对象
     */
    fun getSecretKey(phrase: String): SecretKey {
        val pbeKey = PBEKeySpec(phrase.toCharArray(), salt, 1000000, 256) // AES-256

        return SecretKeySpec(
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(pbeKey).encoded,
            "AES"
        )
    }

    /**
     * 对指定数据采用AES-128算法加密
     *
     * @param data 明文数据
     * @param secretKey 密钥
     * @param iv 见[CryptoUtils.getRandomByteArray]
     * @return 密文数据
     */
    fun encrypt(data: ByteArray, secretKey: SecretKey, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivSpec = IvParameterSpec(iv)

        return try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec).let { cipher.doFinal(data) }
        } catch (e: GeneralSecurityException) {
            throw CryptoException(e.message)
        }
    }

    /**
     * 对指定数据采用AES-128算法加密
     *
     * @param data 密文数据
     * @param secretKey 密钥
     * @param iv 见[CryptoUtils.getRandomByteArray]
     * @return 明文数据
     */
    fun decrypt(data: ByteArray, secretKey: SecretKey, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivSpec = IvParameterSpec(iv)

        return try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec).let { cipher.doFinal(data) }
        } catch (e: GeneralSecurityException) {
            throw CryptoException(e.message)
        }
    }
}