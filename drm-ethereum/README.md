# 环境
Geth基于[go-ethereum-1.11.6](https://github.com/ethereum/go-ethereum/)构建

# genesis.json
`"gasLimit": "0xffffffff",`
- 防止Error: exceeds block gas limit undefined


# 指令
## 初始化
```bash
./geth --datadir ./blockchain-data init genesis.json
```

## 启动
```bash
./geth \
    --datadir ./blockchain-data \
    --networkid 721 \
    --metrics \
    --http \
    --http.port 8545 \
    --http.addr 192.168.10.108 \
    --http.api personal,eth,net \
    --http.corsdomain '*' \
    console 2>>geth.log
```

## 创建用户
```bash
 ./clef --keystore ./blockchain-data/keystore newaccount
```