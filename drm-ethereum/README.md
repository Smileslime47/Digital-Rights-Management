## 部署

1. 在`drm-ethereum/drm-ethereum-image`下执行`docker build -t drm-ethereum:[version]`指令创建Docker镜像
2. 在以太坊节点目录下创建`ethereum`文件夹
3. 将`compose.yaml`和`nginx.conf`放在以太坊节点目录下（和ethereum并列）
4. 将`compose.yaml`中除了nginx外的三个节点的image修改为`drm-ethereum:[version]`
5. 在shell中执行`ip a`指令，寻找名称为`docker0`的网卡，获取docker内网的本机IP地址
6. 将`nginx.conf`中`forward_server`部分的IP修改为本机IP地址
7. 在以太坊节点目录下执行`docker-compose up -d`指令启动容器

### 节点发现

在docker节点中执行`cat /root/.ethereum/geth/nodekey`获取nodekeyhex，执行`bootnode -nodekey /root/.ethereum/geth/nodekey -writeaddress`获取enode地址

在compose.yaml中，通过`--nodekeyhex`指定该节点的nodekeyhex,通过`--bootnodes`指定根节点的enode地址

## 单机脱离容器运行
```
./geth \
   --datadir ./drm-ethereum \
   --bootnodes "enode://d184b2ed048e114ab581175964eb19dff717f540dc203dabe807a1f46c9220b7d495e675184bfd665b96e17c121cc45ccd7b20d195842aff1fca9df6e8b66282@172.29.0.2:30303" \
   --port 30304 \
   --networkid 721 \
   --http \
   --http.addr "0.0.0.0" \
   --http.port 8548 \
   --http.api "eth,net,web3,admin,personal" \
   --http.corsdomain "*" \
   --http.vhosts "*" \
   --mine \
   --miner.threads 2 \
   --miner.etherbase "0x4db3443fc610477a5260c350254c9003b63e0673"
   console \
```

## genesis.json

`"gasLimit": "0xffffffff",`

- 防止Error: exceeds block gas limit undefined
