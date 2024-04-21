package moe._47saikyo.drm.backend.configuration

import io.ktor.server.application.*
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.constant.getProperty
import moe._47saikyo.drm.ipfs.service.IpfsService

fun Application.configureIpfs() =
    IpfsService.init(getProperty(Constant.PropertyUrl.IPFS_ADDRESS)!!)
