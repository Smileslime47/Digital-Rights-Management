## genesis.json

`"gasLimit": "0xffffffff",`

- 防止Error: exceeds block gas limit undefined


## 部署

### 创建镜像

在`./drm-ethereum-image`下执行`docker build -t drm-ethereum:[version]`指令创建Docker镜像

### 修改配置文件

将该目录下compose.yaml中的`image`字段改为`drm-ethereum:[version]`

### 启动

在该目录下执行`docker-compose up -d`指令启动容器

### 节点发现

在docker节点中执行`cat /root/.ethereum/geth/nodekey`获取nodekeyhex，执行`bootnode -nodekey /root/.ethereum/geth/nodekey -writeaddress`获取enode地址

在compose.yaml中，通过`--nodekeyhex`指定该节点的nodekeyhex,通过`--bootnodes`指定根节点的enode地址