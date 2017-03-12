# EDeploy Node
主要实现两个功能

### 灰度发布 
与NGINX部署在同一主机上，收到更新指令后，重新生成nginx.conf配置文件，并实现NGINX优雅重启，即：
> sbin/nginx -s reload

### 应用升级
从指定服务器下载war包，部署到应用服务器【Tomcat】中，然后重启应用

### 服务状态上报
包括：
* nginx
* upstream节点
