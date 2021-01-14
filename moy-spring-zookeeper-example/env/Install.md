* zookeeper集群安装
```
# centos7安装命令
# 每个服务器需要安装jDK8环境依赖
yum install -y java-1.8.0-openjdk wget
# 每个服务器需要打开对应端口防火墙
sudo firewall-cmd --zone=public --add-port=2181/tcp --permanent && \
sudo firewall-cmd --zone=public --add-port=2888/tcp --permanent && \
sudo firewall-cmd --zone=public --add-port=3888/tcp --permanent && \
sudo firewall-cmd --reload

# 搭建三个节点集群,登录一个节点上进行安装
# 选择安装包目录和下载指定版本
mkdir -p /opt/apps &&  cd /opt/apps \
&& wget https://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.5.8/apache-zookeeper-3.5.8-bin.tar.gz

# 解压到指定目录
tar -zxvf apache-zookeeper-3.5.8-bin.tar.gz -C /opt 

# 切换到应用根目录
cd /opt/apache-zookeeper-3.5.8-bin/

# 修改配置名称
cp ./conf/zoo_sample.cfg ./conf/zoo.cfg
# 修改数据目录路径
mkdir -p data && sed -i 's?dataDir=/tmp/zookeeper?dataDir=/opt/apache-zookeeper-3.5.8-bin/data?g' ./conf/zoo.cfg

# 集群配置文件
each 1 > data/myid
# 指定集群机器ip和集群myid信息，server.${创建myid里内容}=${创建myid服务器ip}:2888:3888
echo -e "server.1=10.240.206.34:2888:3888\nserver.2=10.240.206.135:2888:3888\nserver.3=10.240.206.139:2888:3888" >> ./conf/zoo.cfg

# 复制节点并修改集群配置
scp -r /opt/apache-zookeeper-3.5.8-bin root@10.240.206.135:/opt/apache-zookeeper-3.5.8-bin && \
ssh root@10.240.206.135 "echo 2 > /opt/apache-zookeeper-3.5.8-bin/data/myid"

# 复制节点并修改集群配置
scp -r /opt/apache-zookeeper-3.5.8-bin root@10.240.206.139:/opt/apache-zookeeper-3.5.8-bin && \
ssh root@10.240.206.139 "echo 3 > /opt/apache-zookeeper-3.5.8-bin/data/myid"

```

* zookeeper 相关命令
```
# 进入根目录
cd /opt/apache-zookeeper-3.5.8-bin
# 启动
./bin/zkServer.sh start
# 停止
./bin/zkServer.sh stop
# 查看状态
./bin/zkServer.sh status

```

* 启动脚本
```
#!/bin/bash
SERVERS="10.240.206.34 10.240.206.135 10.240.206.139"
ZKHOME="/opt/apache-zookeeper-3.5.8-bin"
PARAM="status"

if [ "" = "$1" ] ;then
    echo "PARAM = $PARAM"
else
    PARAM=$1
fi

echo "===== exc cmd begin ... ====="

for server in $SERVERS
do
    ssh $server "source /etc/profile;$ZKHOME/bin/zkServer.sh $PARAM"
done

echo "===== exc cmd end! ====="

```