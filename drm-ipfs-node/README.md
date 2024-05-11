## 部署

1. 在IPFS节点目录下创建`ipfs`文件夹，并在文件夹下创建`node0/data`和`node0/staging`(其他节点以此类推)目录
2. 将`./ipfs-swarm-key-gen`下的swarm.key文件拷贝到上一步创建的`ipfs/node0/data`(其他节点以此类推)下
3. 将`compose.yaml`和`nginx.conf`放在IPFS节点目录下（和ethereum并列）
4. 在shell中执行`ip a`指令，寻找名称为`docker0`的网卡，获取docker内网的本机IP地址
5. 将`nginx.conf`中`forward_server`部分的IP修改为本机IP地址
6. 在IPFS节点目录下执行`docker-compose up -d`指令启动容器
7. 以node_0作为根节点为例，执行`docker exec ipfs_node_0 ipfs id`，获取address字段下的`/ip4/tcp`地址
   - 假设这里的地址是“/ip4/172.18.0.3/tcp/4001/p2p/12D3KooWJBdvqjcMutw74SRY8npZA88gQQYPdyiodYCkHywxG3bf”
8. 以node_1作为叶节点为例，执行`docker exec ipfs_node_1 ipfs bootstrap add /ip4/172.18.0.3/tcp/4001/p2p/12D3KooWJBdvqjcMutw74SRY8npZA88gQQYPdyiodYCkHywxG3bf`连接到根节点

## 附录：获取Nginx配置文件

```bash
docker run \
  --rm \
  --entrypoint=cat nginx /etc/nginx/nginx.conf > ~/nginx.conf
```