package moe._47saikyo

import moe._47saikyo.contract.DRManager
import moe._47saikyo.contract.Right
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

fun main(){
    BlockChain.connect(
        socket = "http://192.168.10.108:8545",
        walletSource = "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-backend/src/main/resources/KeyStore/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
        password = "1234567890",
        chainId = 721
    )
    val addr = DRManager.deploy(
        BlockChain.web3jInstance,
        BlockChain.bankTxManager,
        DefaultGasProvider()
    ).send().contractAddress
    val manager = DRManager.load(
        addr,
        BlockChain.web3jInstance,
        BlockChain.bankTxManager,
        DefaultGasProvider()
    )
    val rightAddr = Right.deploy(
        BlockChain.web3jInstance,
        BlockChain.bankTxManager,
        DefaultGasProvider(),
        BigInteger.valueOf(123),
        "456"
    ).send().contractAddress
    println(rightAddr)
    manager.addRight(rightAddr).send()
    val right = Right.load(
        manager.getRight("123").send(),
        BlockChain.web3jInstance,
        BlockChain.bankTxManager,
        DefaultGasProvider()
    )
    println(right.rightName.send())
}