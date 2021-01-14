package org.moy.spring;

import org.junit.runner.RunWith;
import org.moy.spring.zookeeper.client.ZookeeperClientApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> 功能测试
 * Created on 2018/9/10
 *
 * @author 叶向阳
 * @since 1.0
 */
@SpringBootTest(classes = {ZookeeperClientApplication.class})
@RunWith(SpringRunner.class)
public abstract class BaseTest {
    protected Logger LOG = LoggerFactory.getLogger(getClass());
}
