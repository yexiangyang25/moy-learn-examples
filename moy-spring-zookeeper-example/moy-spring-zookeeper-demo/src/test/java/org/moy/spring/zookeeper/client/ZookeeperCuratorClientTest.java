package org.moy.spring.zookeeper.client;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.moy.spring.BaseTest;


public class ZookeeperCuratorClientTest extends BaseTest {

    @Test
    public void deleteTest() throws Exception {
        String path = "/myTest";
        delete(path);
    }

    @Test
    public void existsTest() throws Exception {
        String path = "/myTest";
        System.out.println(exists(path));
    }

    @Test
    public void addTest() throws Exception {
        String path = "/myTest";
        String data = "hello, zk";
        System.out.println(add(path, data));
    }

    @Test
    public void updateTest() throws Exception {
        String path = "/myTest";
        String data = "hello, update zk";
        System.out.println(updateNodeData(path, data));
    }


    @Test
    public void getTest() throws Exception {
        String path = "/myTest";
        System.out.println(new String(get(path)));
    }


    public boolean exists(String path) throws Exception {
        try (CuratorFramework client = newClient();) {
            client.start();
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    private Stat updateNodeData(String path, String data) throws Exception {
        try (CuratorFramework client = newClient();) {
            client.start();
            return client.setData().inBackground().forPath(path, data.getBytes());
        }
    }

    private void delete(String path) throws Exception {
        try (CuratorFramework client = newClient();) {
            client.start();
            client.delete().deletingChildrenIfNeeded().forPath(path);
        }
    }

    private byte[] get(String path) throws Exception {
        try (CuratorFramework client = newClient();) {
            client.start();
            return client.getData().forPath(path);
        }
    }

    private String add(String path, String data) throws Exception {
        try (CuratorFramework client = newClient();) {
            client.start();
            return client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, data.getBytes());
        }
    }

    private CuratorFramework newClient() {
        return CuratorFrameworkFactory.builder()
                .connectString("10.240.206.121:2181,10.240.206.158:2181,10.240.206.194:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
                .sessionTimeoutMs(60 * 1000) //会话超时时间，默认60秒
                .namespace("test") //设置命名空间
                .build();
    }
}
