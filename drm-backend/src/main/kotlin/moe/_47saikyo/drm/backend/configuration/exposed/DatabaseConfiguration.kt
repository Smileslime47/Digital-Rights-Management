package moe._47saikyo.drm.backend.configuration.exposed

import io.ktor.server.application.*
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.constant.getProperty
import org.jetbrains.exposed.sql.Database

/**
 * 数据库初始化
 *
 * @author 刘一邦
 * @since 2024/01/05
 */
fun Application.configureDataSource() = Database.connect(
    getProperty(Constant.PropertyUrl.DATASOURCE_URL)!!,
    getProperty(Constant.PropertyUrl.DATASOURCE_DRIVER)!!,
    getProperty(Constant.PropertyUrl.DATASOURCE_USERNAME)!!,
    getProperty(Constant.PropertyUrl.DATASOURCE_PASSWORD)!!)