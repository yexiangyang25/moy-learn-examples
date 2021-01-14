package org.moy.spring.zookeeper.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class ZookeeperCuratorClient {

    @Value("10.240.206.135:2181,10.240.206.139:2181,10.240.206.34:2181")
    private String connectString;
    @Value("1000")
    private int baseSleepTimeMs;
    @Value("3")
    private int maxRetries;
    @Value("6000")
    private int sessionTimeoutMs;
    @Value("6000")
    private int connectionTimeoutMs;

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        // to build curatorClient
        CuratorFramework curatorClient = CuratorFrameworkFactory.builder().connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy).build();
        return curatorClient;
    }

    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeoutMs, (event) -> {
            System.out.printf("%s ---> %s\n", event.getType(), event.getPath());
        });
        return zooKeeper;
    }
}
