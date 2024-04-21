package moe._47saikyo.drm.backend.configuration.security

import org.mindrot.jbcrypt.BCrypt

/**
 * 基于BCrypt框架的加密器
 *
 * @author 刘一邦
 */
class BCryptPasswordEncoder : PasswordEncoder {
    override fun hashPassword(pwd: String): String =
        BCrypt.hashpw(pwd, BCrypt.gensalt())

    override fun verifyPassword(pwd: String, hashedPwd: String): Boolean =
        BCrypt.checkpw(pwd,hashedPwd)
}