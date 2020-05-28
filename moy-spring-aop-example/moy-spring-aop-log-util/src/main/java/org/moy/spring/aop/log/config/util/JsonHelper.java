package org.moy.spring.aop.log.config.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * <p>Description: [JSON 帮助类]</p>
 * Created on 2018/7/5
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class JsonHelper {

    private JsonHelper(){}

    /**
     * 转化JSON字符串
     * @param o
     * @return
     */
    public static String toJsonString(Object o) {
        return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
    }
}
