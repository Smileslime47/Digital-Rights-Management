## 编译合约

### 安装solc(solidity编译器)

```bash
yay -S solidity
```

### 编译

```bash
solc --evm-version paris ./src/main/solidity/DigitalRights.sol --bin --abi --optimize -o ./solidity-build
```
**关于指定EVM版本号为Paris**:

[最新Shanghai版本的EVM中添加了PUSH0操作码，但是该操作码仅能在公共链上正常使用](https://forum.openzeppelin.com/t/unable-to-deploy-from-remix-invalid-opcode-push0/38054/3)

> This is because solidity 0.8.20 introduces the PUSH0(0x5f) opcode which is only supported on ETH mainnet and not on any other chains. That's why other chains can't find the PUSH0(0x5f) opcode and throw this error.

### 安装web3j CLI

```bash
curl -L get.web3j.io | sh && source ~/.web3j/source.sh
```

- 可能需要手动安装jq指令

### 生成Java对象

```bash
export project_path=./src/main/java &&
web3j generate solidity \
    -b ./solidity-build/DRManager.bin \
    -a ./solidity-build/DRManager.abi \
    -o $project_path \
    -p moe._47saikyo.contract &&
web3j generate solidity \
-b ./solidity-build/Right.bin \
-a ./solidity-build/Right.abi \
-o $project_path \
-p moe._47saikyo.contract &&
web3j generate solidity \
-b ./solidity-build/License.bin \
-a ./solidity-build/License.abi \
-o $project_path \
-p moe._47saikyo.contract
```

## 注意事项

- 至少维护一台矿机来达成交易

## 参考

[以太坊官方文档-智能合约](https://ethereum.org/zh/developers/docs/smart-contracts/)

[Solidity Documentation](https://docs.soliditylang.org/en/latest/)

[Web3j Deploy and Interact with Smart Contracts](https://docs.web3j.io/4.11.0/getting_started/deploy_interact_smart_contracts/)