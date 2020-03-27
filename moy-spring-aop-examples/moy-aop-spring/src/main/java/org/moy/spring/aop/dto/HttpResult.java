package org.moy.spring.aop.dto;


import org.moy.spring.aop.log.config.ExceptionToResult;

/**
 * <p>Description: [结果类]</p>
 * Created on 2018/7/5
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class HttpResult<T> implements ExceptionToResult {
    private Boolean isOk;
    private T data;
    private String msgCode;
    private String msg;

    public HttpResult() {
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> HttpResult<T> success(T t) {
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setData(t);
        httpResult.setOk(true);
        httpResult.setMsg("success");
        httpResult.setMsgCode("success");
        return httpResult;
    }

    @Override
    public Object format(Throwable e) {
        HttpResult<?> httpResult = new HttpResult<>();
        httpResult.setOk(false);
        httpResult.setData(null);
        httpResult.setMsg(e.getMessage());
        httpResult.setMsgCode(e.getMessage());
        return httpResult;
    }
}
