# 编译合约
## 安装solc
```bash
yay -S solidity
```

## 编译
```bash
solc --evm-version paris ./contract/DigitalRights.sol --bin --abi --optimize -o ./contract/build
```
### 关于指定EVM版本号为Paris
[最新Shanghai版本的EVM中添加了PUSH0操作码，但是该操作码仅能在公共链上正常使用](https://forum.openzeppelin.com/t/unable-to-deploy-from-remix-invalid-opcode-push0/38054/3)
> This is because solidity 0.8.20 introduces the PUSH0(0x5f) opcode which is only supported on ETH mainnet and not on any other chains. That's why other chains can't find the PUSH0(0x5f) opcode and throw this error.

## 安装web3j CLI
```bash
curl -L get.web3j.io | sh && source ~/.web3j/source.sh
```

## 生成Kotlin对象
```bash
export project_path=./src/main/java &&
web3j generate solidity \
    -b ./contract/build/Account.bin \
    -a ./contract/build/Account.abi \
    -o $project_path \
    -p moe._47saikyo &&
web3j generate solidity \
-b ./contract/build/Right.bin \
-a ./contract/build/Right.abi \
-o $project_path \
-p moe._47saikyo &&
web3j generate solidity \
-b ./contract/build/License.bin \
-a ./contract/build/License.abi \
-o $project_path \
-p moe._47saikyo
```

# 注意事项
- 记得需要挖坑来确认交易

# 参考
[以太坊-智能合约](https://ethereum.org/zh/developers/docs/smart-contracts/)

[Solidity Documentation](https://docs.soliditylang.org/en/latest/)

[Web3j Deploy and Interact with Smart Contracts](https://docs.web3j.io/4.11.0/getting_started/deploy_interact_smart_contracts/)