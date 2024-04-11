FROM ethereum/client-go:alltools-v1.11.6
LABEL authors="smile_slime_47"

# 这里的目录应当和数据目录（datadir）区分开，compose.yaml在挂载数据目录时会将被挂载目录清空
# 从而导致初始化数据丢失的情况，这样会导致区块链自动连接到chainId 1（即mainnet）上
WORKDIR /root/init

ADD genesis.json genesis.json
ADD --chmod=755 run.sh /root/init/run.sh

ENTRYPOINT ["/root/init/run.sh"]
