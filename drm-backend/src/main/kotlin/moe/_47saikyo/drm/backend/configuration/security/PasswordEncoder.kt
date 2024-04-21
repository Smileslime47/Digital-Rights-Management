package moe._47saikyo.drm.backend.configuration.security

interface PasswordEncoder {
    fun hashPassword(pwd:String):String
    fun verifyPassword(pwd:String,hashedPwd:String):Boolean
}