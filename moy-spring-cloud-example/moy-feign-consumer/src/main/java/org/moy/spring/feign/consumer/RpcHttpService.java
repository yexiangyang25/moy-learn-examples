package org.moy.spring.feign.consumer;

import org.moy.spring.common.service.HelloHttpService;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * <p>Description: [反向代理 应用服务 进一步封装REST服务]</p>
 * Created on 2018/12/02
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
@FeignClient(value = "moy-eureka-client", fallback = RpcHttpServiceDefaultFallbackImpl.class)
public interface RpcHttpService extends HelloHttpService {
}
