package org.moy.spring.feign.consumer;

import org.moy.spring.common.service.HelloHttpServiceDefaultFallbackImpl;
import org.springframework.stereotype.Component;

/**
 * <p>Description: [服务降级默认实现]</p>
 * Created on 2020/2/24
 *
 * @author <a href="mailto: yexiangyang@cn.wilmar-intl.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2020 丰益（上海）信息技术有限公司
 */
@Component
public class RpcHttpServiceDefaultFallbackImpl extends HelloHttpServiceDefaultFallbackImpl implements RpcHttpService {
}
