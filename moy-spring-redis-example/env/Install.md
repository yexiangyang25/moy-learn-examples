* zookeeper集群安装
```
IP地址           主机名 
192.168.182.xxx   zk_node_1  
192.168.182.xxx   zk_node_2  
192.168.182.xxx   zk_node_3  

# 每个服务器需要打开对应端口防火墙
sudo firewall-cmd --zone=public --add-port=6379/tcp --permanent && \
sudo firewall-cmd --zone=public --add-port=26379/tcp --permanent && \
sudo firewall-cmd --zone=public --add-port=7000/tcp --permanent && \
sudo firewall-cmd --zone=public --add-port=7001/tcp --permanent && \
sudo firewall-cmd --reload

# redis 编译依赖
yum -y install  gcc gcc-c++ tcl

# 下载安装到指定路径
mkdir -p /opt/packages/ && cd /opt/packages/ && \
 wget https://download.redis.io/releases/redis-5.0.10.tar.gz

# 解压到安装路径
tar -zxvf redis-5.0.10.tar.gz -C /opt

# 进入安装目录
cd /opt/redis-5.0.10/

# 编译，编译出错可以使用：make MALLOC=libc
make
 
# 安装、可以指定安装目录： make install PREFIX=/usr/local/bin/ 
make install

# 将安装好的执行文件和源码远程复制到其他服务器节点上 
scp -r /usr/local/bin/redis* root@zk_node_2:/usr/local/bin/
scp -r /opt/redis-5.0.10 root@zk_node_2:/opt/ 

# 删除redis：rm -f /usr/local/bin/redis*


#==== 主从模式
# 一主二从
# 创建主节点配置文件
mkdir -p /opt/redis-5.0.10/6379 && tee /opt/redis-5.0.10/6379/redis.conf << EOF
# 端口
port    6379
# 后台启动
daemonize yes
# 修改可以远程访问
bind 0.0.0.0
# 修改保护模式
protected-mode no
# 取消密码取消下面两行注释
requirepass 123456
masterauth 123456
# 持久化文件名字
dbfilename dump.rdb
# 日志文件名
logfile /opt/redis-5.0.10/6379/redis.log
# 路径
dir /opt/redis-5.0.10/6379
# 进程信息
pidfile /var/run/redis_6379.pid
EOF

# 将主机点配置远程复制其他服务器节点 当作2从节点配置文件
# 直接复制主节点配置文件，最后一行设置主节点的ip端口即可：replicaof zk_node_1 6379
scp -r /opt/redis-5.0.10/6379 root@zk_node_2:/opt/redis-5.0.10
ssh root@zk_node_2 'echo -e "replicaof zk_node_1 6379" >> /opt/redis-5.0.10/6379/redis.conf'

# 依次启动每台服务器的实例
redis-server /opt/redis-5.0.10/6379/redis.conf

# 启动客户端
redis-cli -h 127.0.0.1 -p 6379 


#==== 哨兵模式
# 一主二从，三哨兵
# 再上一步搭建主从基础上，搭建哨兵集群，就是加上主节点信息进行监听即可
# 创建哨兵配置文件
mkdir -p /opt/redis-5.0.10/26379 && tee /opt/redis-5.0.10/26379/sentinel.conf << EOF
# 端口
port    26379
# 后台启动
daemonize yes
# 修改可以远程访问
bind 0.0.0.0
# 修改保护模式
protected-mode no
# 日志文件名
logfile /opt/redis-5.0.10/26379/sentinel.log
# 路径
dir /opt/redis-5.0.10/26379
# 进程信息
pidfile /var/run/redis_26379.pid
# 配置需要监听主节点信息和密码，可以通过客户端执行
sentinel monitor mymaster zk_node_1 6379 2
sentinel auth-pass mymaster 123456
# 通过客户端执行删除对应监听
# sentinel remove mymaster
EOF

# 将哨兵配置文件直接复制到其他服务器上
scp -r /opt/redis-5.0.10/26379 root@zk_node_2:/opt/redis-5.0.10

# 依次启动每台服务器的哨兵实例
redis-sentinel /opt/redis-5.0.10/26379/sentinel.conf

#==== 集群模式
# 3主3从，本次搭建只有三台，每台启动两个实列，每台实例配置只需要改端口即可
# 创建哨兵配置文件
mkdir -p /opt/redis-5.0.10/cluster/7000 && tee /opt/redis-5.0.10/cluster/7000/redis.conf << EOF
# 端口
port    7000
# 后台启动
daemonize yes
# 修改可以远程访问
bind 0.0.0.0
# 修改保护模式
protected-mode no
# 取消密码取消下面两行注释
requirepass abcdef
masterauth abcdef
# 开启AOF持久化
appendonly yes
# 启用集群模式
cluster-enabled yes 
# 根据你启用的节点来命名，最好和端口保持一致，这个是用来保存其他节点的名称，状态等信息的
cluster-config-file nodes_7000.conf 
# 超时时间
cluster-node-timeout 5000
# 日志文件名
logfile /opt/redis-5.0.10/cluster/7000/cluster.log
# 路径
dir /opt/redis-5.0.10/cluster/7000
# 进程信息
pidfile /var/run/redis_7000.pid
EOF

# 服务器有限，启动两台实例，再复制一份配置文件，并修改端口
cp -r /opt/redis-5.0.10/cluster/7000 /opt/redis-5.0.10/cluster/7001 && \
 sed -i 's?7000?7001?g' /opt/redis-5.0.10/cluster/7001/redis.conf

# 将两个实例配置文件复制到其他服务器上
scp -r /opt/redis-5.0.10/cluster root@zk_node_2:/opt/redis-5.0.10

# 每台服务器依次启动redis配置实例
redis-server /opt/redis-5.0.10/cluster/7000/redis.conf && \
 redis-server /opt/redis-5.0.10/cluster/7001/redis.conf
 
# 启动集群,不能输入域名，要ip，
# -a后追加密码，--cluster-replicas 指定从节点数量 
# 提示后输入:yes， 前三个为主节点，后三个为从节点
redis-cli --cluster create \
 10.240.206.221:7000 10.240.206.225:7000 10.240.206.227:7000 \
 10.240.206.221:7001 10.240.206.225:7001 10.240.206.227:7001 \
 --cluster-replicas 1 -a abcdef

# 集群模式下要带参数 -c，表示集群
redis-cli -p 7100 -c

# 关闭命令: 每台服务器依次关闭集群命令
# redis-cli -c -h zk_node_1 -p 7000 shutdown && redis-cli -c -h zk_node_1 -p 7001 shutdown

# 强制重置初始状态，将每个节点下aof、rdb、nodes.conf本地备份文件删除:
# 删除指定路径下，修改时间为1天内，匹配的文件 
# find /opt/redis-5.0.10/cluster/ -mtime -1 -name "*.aof" -exec rm -rf {} \;
# find /opt/redis-5.0.10/cluster/ -mtime -1 -name "*.rdb" -exec rm -rf {} \;
# find /opt/redis-5.0.10/cluster/ -mtime -1 -name "nodes_*.conf" -exec rm -rf {} \;
# 批量强制关闭redis相关进程：kill -9 $(pidof redis-server redis-sentinel)
# 强制清除本次安装redis信息：rm -rf /opt/redis-5.0.10 /usr/local/bin/redis*
```