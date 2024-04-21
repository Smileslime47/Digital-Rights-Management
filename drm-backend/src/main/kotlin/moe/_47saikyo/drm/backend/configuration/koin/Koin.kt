package moe._47saikyo.drm.backend.configuration.koin

import io.ktor.server.application.*
import moe._47saikyo.drm.blockchain.configuration.koin.KoinBlockChainWrapperConfiguration
import moe._47saikyo.drm.ipfs.configuration.koin.KoinIpfsConfiguration
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

/**
 * 依赖注入初始化
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
fun Application.configureKoin(){
    install(Koin){
        slf4jLogger()
        modules(KoinBackendConfiguration.module)
        modules(KoinBlockChainWrapperConfiguration.module)
        modules(KoinIpfsConfiguration.module)
    }
}