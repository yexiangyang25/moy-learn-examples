package org.moy.spring.zookeeper.client;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.moy.spring.BaseTest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ZookeeperTest extends BaseTest {

    private static String connectString = "10.240.206.121:2181,10.240.206.158:2181,10.240.206.194:2181";
    private static int sessionTimeout = 60 * 1000;
    private ZooKeeper zkClient;
    private static String testPath = "/hello world";

    @Before
    public void before() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, (event) -> {
            System.out.printf("%s ---> %s\n", event.getType(), event.getPath());
            try {
                zkClient.getChildren("/", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addNode() throws KeeperException, InterruptedException {
        String node = zkClient.create(testPath, "hello zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.printf(node);
    }

    @Test
    public void getAllNode() throws KeeperException, InterruptedException {
        List<String> childrenNode = zkClient.getChildren("/", true);
        for (String nodePath : childrenNode) {
            System.out.println(nodePath);
        }
    }

    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData(testPath, true, null);
        System.out.printf("%s ---> %s\n", testPath, new String(data));

    }

    @Test
    public void setData() throws KeeperException, InterruptedException {
        if (Objects.nonNull(zkClient.exists(testPath, false))) {
            Stat stat = zkClient.setData(testPath, "hello world".getBytes(), -1);
            System.out.println(stat);
        }
    }

    @Test
    public void deleteNode() throws KeeperException, InterruptedException {
        if (Objects.nonNull(zkClient.exists(testPath, false))) {
            zkClient.delete(testPath, -1);
        }
    }
}
