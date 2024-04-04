package utils

import moe._47saikyo.utils.CryptoUtils
import kotlin.test.Test

/**
 * CryptoUtilsTest
 *
 * @author 刘一邦
 */
class CryptoUtilsTest {
    /**
     * 测试获取密钥
     */
    @Test
    fun getSecretKeyTest() {
        //测试密钥长度
        assert(CryptoUtils.getSecretKey("password").encoded.size == 32 * 8)//256比特

        //测试相同密码生成的密钥是否相同
        assert(CryptoUtils.getSecretKey("password") == CryptoUtils.getSecretKey("password"))
    }

    /**
     * 测试加密解密
     */
    @Test
    fun cryptoTest() {
        val iv = CryptoUtils.getRandomByteArray(CryptoUtils.IV_128_BIT)

        val encryptedData = CryptoUtils.encrypt(
            "encryptData".toByteArray(),
            CryptoUtils.getSecretKey("password"),
            iv
        )

        val decryptedData = CryptoUtils.decrypt(
            encryptedData,
            CryptoUtils.getSecretKey("password"),
            iv
        )

        assert(decryptedData.contentEquals("encryptData".toByteArray()))
    }
}