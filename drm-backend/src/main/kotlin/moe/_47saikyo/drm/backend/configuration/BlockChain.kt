package moe._47saikyo.drm.backend.configuration

import io.ktor.server.application.*
import moe._47saikyo.drm.backend.constant.Constant
import moe._47saikyo.drm.backend.constant.getProperty
import moe._47saikyo.drm.blockchain.BlockChain
import moe._47saikyo.drm.blockchain.BlockChainConfigurationBuilder

/**
 * 配置连接以太坊区块链
 *
 * @author 刘一邦
 */
fun Application.configureBlockChain() {
//    val walletFilePath = Application::class.java.getResource(
//        getProperty(Constant.PropertyUrl.CHAIN_WALLET_FILE)!!
//    )?.path
    BlockChain.connect(
        BlockChainConfigurationBuilder()
            .withChain(
                socket = getProperty(Constant.PropertyUrl.CHAIN_SOCKET)!!,
                chainId = getProperty(Constant.PropertyUrl.CHAIN_ID)!!.toLong()
            )
            .withBankWallet(
                source = getProperty(Constant.PropertyUrl.CHAIN_WALLET_FILE)!!,
                password = getProperty(Constant.PropertyUrl.CHAIN_PASSWORD)!!
            )
//            //自定义Gas价格和Gas限制
//            .withGasProvider(
//                gasPrice = getProperty(Constant.PropertyUrl.CHAIN_GAS_PRICE)!!.toBigInteger(),
//                gasLimit = getProperty(Constant.PropertyUrl.CHAIN_GAS_LIMIT)!!.toBigInteger()
//            )
            .withManager(getProperty(Constant.PropertyUrl.CHAIN_MANAGER)!!)
            .build()
    )
}