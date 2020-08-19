##### 学习案例
* 本地启动Jar包
```
  java -XX:+PrintGCDetails -Xms128m -Xmx128m -jar app.jar  
```
* 服务端启动Jar包
```
  nohup java -server -Xms128m -Xmx128m -jar app.jar >/dev/null &  
```