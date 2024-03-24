package moe._47saikyo

import moe._47saikyo.utils.CryptoUtils
import java.util.Base64
import javax.crypto.spec.SecretKeySpec

fun main(){
    val iv = Base64.getDecoder().decode("ZR7qTzBJiN/Lq7BrpC53GA==")

    println(String(iv))

    val secretKey = CryptoUtils.getSecretKey("123")
    val walletFile = "{\"address\":\"fdd71114061494a053b2ef4cd7e6242e9459ca2b\",\"id\":\"89595057-c10e-41d9-9ad4-a03ae108dbce\",\"version\":3,\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"9970188930abd777375be8f37931c39cc18098ab7c036691c0a59dc9c0d54015\",\"cipherparams\":{\"iv\":\"d930212c1cc1b18a5caa9e2c57a349d6\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"ee516fc5ab522794876bfaa37afaa25b9f728622309a2fb1e6cd4b22d91d6a85\"},\"mac\":\"806a76b5386edeae53043fce2f2c61bf40cad604326fa561534aebd253254000\"}}\n"

    val encryptedWalletFile= CryptoUtils.encrypt(
        data = walletFile.toByteArray(),
        secretKey = secretKey,
        iv = iv
    )

    println(String(encryptedWalletFile))

    val test = CryptoUtils.decrypt(
        data = encryptedWalletFile,
        secretKey = secretKey,
        iv = iv
    )

    println(String(test))



//    BlockChain.connect(
//        socket = "http://192.168.10.108:8545",
//        walletSource = "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-backend/src/main/resources/KeyStore/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
//        password = "1234567890",
//        chainId = 721
//    )
//    val addr = DRManager.deploy(
//        BlockChain.web3jInstance,
//        BlockChain.bankTxManager,
//        DefaultGasProvider()
//    ).send().contractAddress
//    val manager = DRManager.load(
//        addr,
//        BlockChain.web3jInstance,
//        BlockChain.bankTxManager,
//        DefaultGasProvider()
//    )
//    val rightAddr = Right.deploy(
//        BlockChain.web3jInstance,
//        BlockChain.bankTxManager,
//        DefaultGasProvider(),
//        BigInteger.valueOf(123),
//        "456"
//    ).send().contractAddress
//    println(rightAddr)
//    manager.addRight(rightAddr).send()
//    val right = Right.load(
//        manager.getRight("123").send(),
//        BlockChain.web3jInstance,
//        BlockChain.bankTxManager,
//        DefaultGasProvider()
//    )
//    println(right.rightName.send())
}