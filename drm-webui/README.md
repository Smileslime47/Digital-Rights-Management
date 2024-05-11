## 构建

1. 将`.env.production`下的`VITE_BASE_URL`修改为后端节点地址
2. 在`drm-webui`下执行`npm run build`指令获取构建文件
3. 将`drm-webui/dist`目录覆盖到`drm-webui-image`目录下
4. 在`drm-webui-image`下执行`docker build -t drm-webui:[version]`指令创建Docker镜像

## 部署

1. 将`compose.yaml`放在前端节点目录下
2. 将`compose.yaml`中节点的image修改为`drm-webui:[version]`
3. 在前端节点目录下执行`docker-compose up -d`指令启动容器