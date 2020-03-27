package org.moy.spring.aop.dto;


import org.moy.spring.aop.log.config.ExceptionToResult;

/**
 * <p>Description: [测试 返回值]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class Demo2 implements ExceptionToResult {

    private boolean isOk = true;
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    @Override
    public Object format(Throwable e) {
        Demo2 demo2 = new Demo2();
        demo2.isOk = false;
        return demo2;
    }

    @Override
    public String toString() {
        return "Demo2{" +
                "isOk=" + isOk +
                '}';
    }
}
