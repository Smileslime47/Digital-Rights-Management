## 构建

1. 将后端`drm-backend`中资源文件夹下的的`application.yaml`中的`global.chain.manager`字段修改为合约地址
2. 将后端`drm-backend`中资源文件夹下的的`application.yaml`中的`ktor.environment`修改为`prod`
3. 将后端`drm-backend`中资源文件夹下的的`application.yaml`中的`prod`下的`exposed.datasource.jdbcurl`、`chain.socket`和`ipfs.address`中的地址修改为对应节点的IP地址
   - 如所有节点都部署在单机上，则地址同Ethereum节点部分的docker0地址，不能填localhost或者127.0.0.1，因为不同的docker容器之间无法通过localhost访问
   - 其中ipfs地址格式形如：/ip4/172.17.0.1/tcp/5001
   - chain.socket和ipfs.address均应当填写集群的Nginx监听地址，而非真实的节点地址
4. 将后端`drm-backend`中资源文件夹下的的`application.yaml`中的`global.chain`下的`address`字段和`password`字段修改为银行账户的地址和密码
5. 将后端`drm-backend`中资源文件夹下的的`application.yaml`中的`global.exposed.datasource`下的`username`字段和`password`字段修改为数据库的用户名和密码
6. 在`drm-backend`下执行`gradle buildFatJar`指令获取构建文件
7. 将整个build目录覆盖到`drm-backend-image`下
8. 在`drm-backend-image`下执行`docker build -t drm-backend:[version]`指令创建Docker镜像

## 部署

1. 将`compose.yaml`放在后端节点目录下，
2. 将`compose.yaml`中volume部分的左半部分修改为[智能合约构建](../drm-blockchain/README.md#构建)时创建的钱包文件的路径
3. 将`compose.yaml`中节点的image修改为`drm-backend:[version]`
4. 在后端节点目录下执行`docker-compose up -d`指令启动容器