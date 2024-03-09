package moe._47saikyo.models

import org.mindrot.jbcrypt.BCrypt


data class UserCredential (val username:String,val password:String){
    fun hashedPassword():String = BCrypt.hashpw(password,BCrypt.gensalt())
}