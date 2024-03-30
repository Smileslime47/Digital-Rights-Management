package moe._47saikyo

import io.ktor.server.application.*
import io.ktor.server.netty.*
import moe._47saikyo.configuration.configureBlockChain
import moe._47saikyo.configuration.configureHTTP
import moe._47saikyo.configuration.configureSerialization
import moe._47saikyo.configuration.exposed.configureDataSource
import moe._47saikyo.configuration.koin.configureKoin
import moe._47saikyo.configuration.security.configureSecurity
import moe._47saikyo.controller.*


/**
 * 启动类
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
fun main(args: Array<String>): Unit = EngineMain.main(args)
fun Application.modules() {
    //依赖配置
    configureKoin()
    configureHTTP()
    configureSecurity()
    configureSerialization()
    //数据库配置
    configureDataSource()
    //区块链API配置
    configureBlockChain()
    //项目内配置
    userController()
    groupController()
    chainAccountController()
    chainRightController()
    noticeController()
}
