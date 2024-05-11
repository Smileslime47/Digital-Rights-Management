## 构建

1. 运行`drm-blockchain`下的`solidity-compile.sh`，将合约代码编译为Java对象
2. 执行`drm-blockchain`中单元测试下的`PreDeploy`测试类的`newAccount`函数，为银行账户创建一个新的钱包
3. 将日志输出的钱包文件保存在一个Json文件下
4. 在以太坊目录下运行`docker compose down`关闭以太坊容器
5. 修改以太坊目录下的compose.yaml文件，将`node_0`和`node_1`的command下`--miner.etherbase`的值修改为银行账户的地址
6. 在以太坊节点目录下执行`docker-compose up -d`指令重新启动容器并开始挖矿
7. 修改`drm-blockchain`中单元测试下的`BlockChainTest`测试类的`init`函数的连接配置
    - `withChain`：修改以太坊容器集群中的nginx监听地址
    - `withBankWallet`：修改银行账户的钱包文件路径及密码
8. 执行`drm-blockchain`中单元测试下的`PreDeploy`测试类的`deployManager`函数，将平台合约部署到区块链上并获取合约地址

## 编译合约

> 2024/03/20：在安装完相关环境后，可以直接运行目录下的`solidity-compile.sh`完成编译及生成Java对象的操作，但solc和web3j CLI仍需要提前安装

### 安装solc(solidity编译器)

```bash
yay -S solidity
```

### 编译

```bash
solc --evm-version paris ./src/main/solidity/DRManager.sol --bin --abi --optimize -o ./solidity-build
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
```

## 注意事项

- 至少维护一台矿机来达成交易

## 参考

[以太坊官方文档-智能合约](https://ethereum.org/zh/developers/docs/smart-contracts/)

[Solidity Documentation](https://docs.soliditylang.org/en/latest/)

[Web3j Deploy and Interact with Smart Contracts](https://docs.web3j.io/4.11.0/getting_started/deploy_interact_smart_contracts/)