package org.moy.spring.common.service;

import org.moy.spring.common.pojo.Demo;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description: [HelloService 定义接口]</p>
 * Created on 2018/12/04
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public interface HelloHttpService {

    /**
     * 默认方法
     *
     * @return
     */
    @GetMapping("/hello/index")
    String index();


    /**
     * 测试方法
     *
     * @param header
     * @param param
     * @param demo
     * @return
     */
    @PostMapping("/hello/demo")
    Demo demo(@RequestHeader("header") String header,
              @RequestParam("param") String param,
              @RequestBody Demo demo);
}
