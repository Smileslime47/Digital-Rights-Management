package moe._47saikyo

import io.ktor.server.application.*
import io.ktor.server.netty.*
import moe._47saikyo.controller.debugController
import moe._47saikyo.controller.userController
import moe._47saikyo.plugins.exposed.configureDataSource
import moe._47saikyo.plugins.*
import moe._47saikyo.plugins.koin.configureKoin
import moe._47saikyo.plugins.security.configureSecurity

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
    //项目内配置
    userController()
    debugController()
}
