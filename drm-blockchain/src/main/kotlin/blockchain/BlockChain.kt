package moe._47saikyo.blockchain

import org.web3j.protocol.admin.Admin
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import java.net.Socket


class BlockChain {
    companion object{
        val instance =
        fun connect(socket:Socket){
            val addr = "0x5d79b23ada972dbcaa61bddb5ccfa0c85bde3579"
            val web3j = Admin.build(HttpService("http://192.168.10.108:8545"))
            val personalUnlockAccount = web3j.personalUnlockAccount(addr, "123").send()
            if (personalUnlockAccount.accountUnlocked()) {
                val txo = Transaction.createEthCallTransaction(
                    addr,
                    addr,
                    "0x123a"
                )
                val str = web3j.ethSendTransaction(txo).send().transactionHash
                println(str)
            }
        }
    }
}