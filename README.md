# Digital Rights Manager

基于以太坊区块链和IPFS的Ktor项目

该项目旨在通过部署在**以太坊私有链**上的智能合约实现对版权和授权的管理，并通过私有化部署的IPFS节点实现对数字资源的存储

## 开发语言和框架

项目整体通过Gradle管理

- 服务器以及区块链API基于Kotlin开发
  - 服务器框架基于Ktor
  - 依赖注入基于Koin
  - 数据库交互基于Expose
  - 区块链API基于Web3j
- 前端网页基于TypeScript开发
  - 响应式框架基于Vue.js
  - 组件库使用Element UI PLus
- IPFS基于java-ipfs-http-client库
- 智能合约基于Solidity开发
  - EVM版本使用Paris

## 部署流程

### 数据库

数据库构建语句（DDL）见[SQL-DDL文档](./SQL-DDL.md)

### IPFS节点

见`drm-ipfs-node`下[README文档](./drm-ipfs-node/README.md#部署)部署章节

### Ethereum节点

见`drm-ethereum`下[README文档](./drm-ethereum/README.md#部署)部署章节

### 智能合约(依赖于Ethereum节点部署)

见`drm-blockchain`下[README文档](./drm-blockchain/README.md#构建)构建章节

### 后端（依赖于上述部分）

见`drm-backend`下[README文档](./drm-backend/README.md)

### 前端

见`drm-webui`下[README文档](./drm-webui/README.md)

## 核心组件

详见各模块下的README文档（如果有）

### drm-core

核心模块，基于Kotlin

- domain包：包含Kotlin模块之间相互传输的通用数据类
- enums包：包含Kotlin模块中的枚举类
- constant包：应用于Kotlin项目全局的环境变量
- exception包：Kotlin项目中的基类异常（DrmException）

### drm-blockchain

包含与以太坊区块链交互的代码，由Java代码、Kotlin代码和Solidity代码共同组成（Java和Solidity均为智能合约）

- Kotlin with Web3j
  - BlockChain:包含建立与区块链服务器的链接、维护银行账户等基础功能，在调用该类的connect静态方法前，调用其他类会抛出`BlockChainNotConnectedException`异常
  - Estimate：估算部署及调用合约Gas开销的工具类
  - CurrencyConverter：用于将ETH和WEI之间的货币单位转换
  - AccountService：包含区块链账号创建、转账、查询、获取TxManager（用于后续部署智能合约）等功能
  - ManagerService：与平台合约（DRManager）直接交互的Service类
  - RightService/LicenseService：基于ManagerService，提供对版权和授权的管理
- Java
  - **Web3j CLI**转译智能合约得到的Java对象，供其他Kotlin组件调用，详见[README](./drm-blockchain/README.md)
- contract：包含`.sol`格式的智能合约源代码（开发中）及其直接构件（`.bin`和`.abi`），.sol文件通过solc编译为abi和bin文件后，通过web3j转译为Java对象导入到项目中
  - 智能合约编译见[README](./drm-blockchain/README.md)

### drm-ipfs

包含与IPFS节点交互的代码，基于Kotlin和java-ipfs-http-client库

- IpfsService：Ipfs接口定义，此外还包含了连接到IPFS节点的基础功能
- IpfsServiceImpl：包含与IPFS节点交互的基础功能，如上传、下载文件等

### drm-backend

服务器后端模块，包含网络请求和业务逻辑处理的大部分代码，基于Kotlin with Ktor，依赖于drm-core和drm-blockchain组件

- configuration包：服务器组件的配置类
- constant包：应用于服务器的常量值
- controller包：用于处理网络请求的控制器类
- service包：用于处理业务逻辑的Service类
- dao包：基于models包的表映射关系，实现最基础的CRUD函数
- models包：建立domain包下数据类与数据库表之间的映射关系

### drm-webui

前端模块，包含与用户直接进行交互的代码，基于TypeScript with Vue3

- assets：用于存储静态资源
- components：页面组件
- composable：工具函数
- constant：常量和环境变量
- modules：建立起后端传来的对象与前端TypeScript对象的映射关系
- pages：前端页面文件
- route：路由映射
- server：网络请求相关配置类

### drm-ethereum

部署以太坊私有链的相关环境和配置，严格意义上来讲，该模块独立运作，不依赖于其他几个组件，需要单独部署

- geth：以太坊客户端
  - 区块链部署见[README](./drm-ethereum/README.md)
- clef：本地的以太坊账号管理服务

> 现提供DockerCompose部署方案，详见[README](./drm-ethereum/README.md)

### drm-ipfs-node

部署IPFS节点的相关环境和配置，同drm-ethereum,需要单独部署

- IPFS节点部署见[README](./drm-ipfs-node/README.md)

## License

Digital Rights Manager项目由[MIT](./LICENSE)协议进行许可开源