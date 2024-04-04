package moe._47saikyo

import moe._47saikyo.constant.BlockChainConstant
import org.web3j.abi.TypeReference
import java.math.BigInteger

fun main() {
    BlockChain.connect(
        BlockChainConfigurationBuilder()
            .withChain("http://192.168.10.108:8545", 721)
            .withBankWallet(
                "/home/smile_slime_47/Projekt/Digital-Rights-Management/drm-backend/src/main/resources/KeyStore/UTC--2024-03-16T14-15-01.824378618Z--cb7f6d5c8f5c71c3f604f6fec874a97007dfe4fe.json",
                "1234567890"
            )
            .withGasProvider(
                gasLimit = BigInteger.valueOf(1000000),
                gasPrice = BigInteger.valueOf(1000000000)
            )
            .build()
    )


    println(
        Estimate.estimateCall(
            "0xfe5078c71a0fc05ab17429472fb2b310612706e5",
            "addRight",
            listOf(
                address(BlockChainConstant.ADDRESS_PLACEHOLDER)
            ),
            listOf(
                TypeReference.makeTypeReference(BlockChainConstant.SolidityType.ADDRESS)
            )
        )
    )
}