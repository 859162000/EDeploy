# EDeploy Node
与NGINX部署在同一主机上，收到更新指令后，重新生成nginx.conf配置文件，并实现NGINX优雅重启，即：
> sbin/nginx -s reload
