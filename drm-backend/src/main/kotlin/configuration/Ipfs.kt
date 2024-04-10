package moe._47saikyo.configuration

import io.ktor.server.application.*
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.getProperty
import moe._47saikyo.service.IpfsService

fun Application.configureIpfs() =
    IpfsService.init(getProperty(Constant.PropertyUrl.IPFS_ADDRESS)!!)
