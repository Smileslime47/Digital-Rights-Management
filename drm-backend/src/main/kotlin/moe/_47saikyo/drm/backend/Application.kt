package moe._47saikyo.drm.backend

import io.ktor.server.application.*
import io.ktor.server.netty.*
import moe._47saikyo.drm.backend.configuration.configureBlockChain
import moe._47saikyo.drm.backend.configuration.configureHTTP
import moe._47saikyo.drm.backend.configuration.configureIpfs
import moe._47saikyo.drm.backend.configuration.configureSerialization
import moe._47saikyo.drm.backend.configuration.exposed.configureDataSource
import moe._47saikyo.drm.backend.configuration.koin.configureKoin
import moe._47saikyo.drm.backend.configuration.security.configureSecurity
import moe._47saikyo.drm.backend.controller.*


/**
 * 启动类
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.enableAllControllers() {
    //项目内配置
    userController()
    groupController()
    chainAccountController()
    chainRightController()
    chainLicenseController()
    noticeController()
    pendingRightController()
    pendingLicenseController()
    ipfsController()
}
