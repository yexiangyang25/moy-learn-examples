package org.moy.spring.aop.service;

import org.moy.spring.aop.dto.Demo1;
import org.moy.spring.aop.dto.Demo2;
import org.moy.spring.aop.log.config.AppLogConfig;

/**
 * <p>Description: [测试 接口]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface TestService {

    /**
     * sayHi
     * @param name
     * @return
     */
    String sayHi(String name);

    /**
     * 获取实现ExceptionToResult接口的实体
     * @param id
     * @return
     */
    Demo2 getDemo2(Long id);


    /**
     *
     * 获取未实现ExceptionToResult接口的实体
     * @param id
     * @return
     */
    Demo1 getDemo1(Long id);
}
