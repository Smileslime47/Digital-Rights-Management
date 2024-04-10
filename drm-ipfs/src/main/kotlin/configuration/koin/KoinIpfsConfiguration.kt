package configuration.koin

import moe._47saikyo.service.IpfsService
import moe._47saikyo.service.impl.IpfsServiceImpl
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