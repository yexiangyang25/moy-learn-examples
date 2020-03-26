package org.moy.spring.aop.controller;


/**
 * <p>Description: [测试 返回值]</p>
 * Created on 2019/5/20
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class Demo1 {

    private boolean isOk = true;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    @Override
    public String toString() {
        return "Demo1{" +
                "isOk=" + isOk +
                '}';
    }
}
