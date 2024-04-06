## 初始化

手动创建`./ipfs/node/data`和`./ipfs/node/staging`目录

## 设置链ID

将`./ipfs-swarm-key-gen`下的swarm.key文件拷贝到`./ipfs/node/data`下

## 启动IPFS节点

```bash
docker compose up -d
```


## 附录：获取Nginx配置文件

```bash
docker run \
  --rm \
  --entrypoint=cat nginx /etc/nginx/nginx.conf > ~/nginx.conf
```