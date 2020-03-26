package org.moy.spring.aop.log.config.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: [Servlet 帮助类]</p>
 * Created on 2018/7/5
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class ServletHelper {

    private ServletHelper() {
    }

    /**
     * 获取请求URI
     *
     * @return
     */
    public static String getRequestUri() {
        String requestUri = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            requestUri = request.getRequestURI();
        }
        return requestUri;
    }


    /**
     * 设置http响应码500
     */
    public static void setInternalServerError() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletResponse response = servletRequestAttributes.getResponse();
            if (null != response) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}
