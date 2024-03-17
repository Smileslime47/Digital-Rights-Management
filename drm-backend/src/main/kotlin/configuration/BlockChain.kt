package moe._47saikyo.configuration

import io.ktor.server.application.*
import moe._47saikyo.BlockChain
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.getProperty

/**
 * 配置连接以太坊区块链
 *
 * @author 刘一邦
 */
fun Application.configureBlockChain() {
    val walletFilePath = Application::class.java.getResource(
        getProperty(Constant.PropertyUrl.CHAIN_WALLET_FILE)!!
    )?.path
    BlockChain.connect(
        socket = getProperty(Constant.PropertyUrl.CHAIN_SOCKET)!!,
        password = getProperty(Constant.PropertyUrl.CHAIN_PASSWORD)!!,
        walletSource = walletFilePath!!,
        chainId = getProperty(Constant.PropertyUrl.CHAIN_ID)!!.toLong()
    )
}