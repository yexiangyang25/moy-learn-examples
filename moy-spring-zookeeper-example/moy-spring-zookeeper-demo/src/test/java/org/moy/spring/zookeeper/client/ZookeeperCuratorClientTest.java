package org.moy.spring.zookeeper.client;


import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.moy.spring.BaseTest;

import javax.annotation.Resource;


public class ZookeeperCuratorClientTest extends BaseTest {

    @Resource
    CuratorFramework curatorFramework;
    @Resource
    ZooKeeper zooKeeper;


    @Test
    public void test() throws KeeperException, InterruptedException {
        String path = "/test";
        Stat exists = zooKeeper.exists(path, false);
        System.out.println(exists);
    }

    @Test
    public void checkNodeExist() throws Exception {
        curatorFramework.start();
        String path = "/test";
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat != null) {
            throw new RuntimeException("path = " + path + " has bean exist.");
        }
    }
}
