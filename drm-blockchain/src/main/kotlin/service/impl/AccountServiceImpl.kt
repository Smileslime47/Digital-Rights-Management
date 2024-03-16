package moe._47saikyo.service.impl

import moe._47saikyo.Account
import moe._47saikyo.BlockChain
import moe._47saikyo.exception.BlockChainNotConnectedException
import moe._47saikyo.service.AccountService
import org.web3j.tx.gas.DefaultGasProvider


class AccountServiceImpl : AccountService {
    override fun deploy(): String =
        if (!BlockChain.connected) {
            throw BlockChainNotConnectedException("BlockChain not connected")
        } else Account.deploy(
            BlockChain.web3jInstance,
            BlockChain.txManager,
            DefaultGasProvider()
        ).send().contractAddress
}