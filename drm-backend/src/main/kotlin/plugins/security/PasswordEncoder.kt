package moe._47saikyo.plugins.security

interface PasswordEncoder {
    fun hashPassword(pwd:String):String
    fun verifyPassword(pwd:String,hashedPwd:String):Boolean
}