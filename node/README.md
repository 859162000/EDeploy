# EDeploy Node
主要实现两个功能

### 灰度发布 
与NGINX部署在同一主机上，收到更新指令后，重新生成nginx.conf配置文件，并实现NGINX优雅重启，即：
> sbin/nginx -s reload

### 定时上报服务状态
包括：
* nginx
* upstream节点

