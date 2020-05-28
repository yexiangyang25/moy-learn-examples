##### 单节点开发环境
```
docker run --name rabbitmq  -p 15672:15672 -p 5672:5672 -d rabbitmq:3.8.2
```
* 进入容器中安装管理界面
```
docker exec -it rabbitmq bash

rabbitmq-plugins enable rabbitmq_management
```
##### 管理界面
* [rabbitmq-management](http://localhost:15672/index.html)