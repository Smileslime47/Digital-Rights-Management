package moe._47saikyo

import constant.GlobalConstant

class BlockChainConfiguration {
    //以太坊端点地址
    var socket: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //银行账户的KeyFile路径
    var walletSource: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //银行账户密码
    var walletPassword: String = GlobalConstant.NULL_PLACEHOLDER
        private set

    //区块链的链ID,用于开启txManager
    var chainId: Long = 0L
        private set

    //DRManager地址
    var managerAddress: String? = null
        private set

    fun withSocket(socket: String): BlockChainConfiguration {
        this.socket = socket
        return this
    }

    fun withChain(id: Long): BlockChainConfiguration {
        this.chainId = id
        return this
    }

    fun withBankWallet(source: String, password: String): BlockChainConfiguration {
        this.walletSource = walletSource
        this.walletPassword = password
        return this
    }

    fun withManager(addr: String): BlockChainConfiguration {
        this.managerAddress = addr
        return this
    }
}