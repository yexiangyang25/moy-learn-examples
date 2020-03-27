package org.moy.spring.aop.service.impl;

import org.moy.spring.aop.dto.Demo1;
import org.moy.spring.aop.dto.Demo2;
import org.moy.spring.aop.log.config.AppLogConfig;
import org.moy.spring.aop.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>Description: [默认实现]</p>
 * Created on 2020/03/25
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String sayHi(String name) {
        randomException();
        return "hi ," + name;
    }

    @Override
    @AppLogConfig
    public Demo2 getDemo2(Long id) {
        Demo2 demo2 = new Demo2();
        demo2.setId(id);
        demo2.setOk(true);
        randomException();
        return demo2;
    }

    public void randomException() {
        int num = 2;
        Random random = new Random();
        int i = random.nextInt(num);
        if (i % num == 0) {
            int i1 = i / 0;
        }
    }

    @Override
    @AppLogConfig
    public Demo1 getDemo1(Long id) {
        Demo1 demo1 = new Demo1();
        demo1.setId(id);
        demo1.setOk(true);
        randomException();
        return demo1;
    }
}
