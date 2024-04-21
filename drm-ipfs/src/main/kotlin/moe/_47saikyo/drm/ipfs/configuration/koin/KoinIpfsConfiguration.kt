package moe._47saikyo.drm.ipfs.configuration.koin

import moe._47saikyo.drm.ipfs.service.IpfsService
import moe._47saikyo.drm.ipfs.service.impl.IpfsServiceImpl
import org.koin.dsl.module

/**
 * Koin配置类
 *
 * @author 刘一邦
 */
object KoinIpfsConfiguration {
    val module = module {
        single<IpfsService> { IpfsServiceImpl() }
    }
}