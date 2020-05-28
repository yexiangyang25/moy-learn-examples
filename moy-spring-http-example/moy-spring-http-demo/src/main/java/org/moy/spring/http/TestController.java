package org.moy.spring.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/indexPost")
    public String indexPost(
            @RequestHeader(value = "source_sys_id", required = false) String sourceSysId,
            @RequestHeader(value = "target_sys_id", required = false) String targetSysId,
            @RequestHeader(value = "service_name", required = false) String serviceName,
            @RequestHeader(value = "trans_id", required = false) String transId,
            @RequestHeader(value = "biz_event", required = false) String bizEvent,
            @RequestHeader(value = "submit_time", required = false) String submitTime,
            @RequestBody String body) {

        logger.info("{} {} {} {} {} {} {}",
                sourceSysId, targetSysId, serviceName, transId, bizEvent, submitTime, body);

        String result = "{\"E_STATUS\":\"S\",\"E_MESSAGE\":\"查询成功\",\"OUT_LIST\":[{\"DOC_NUM\":\"6545001234\",\"WERKS\":\"ZA01\",\"LGORT\":\"G065\"},{\"DOC_NUM\":\"6545008888\",\"WERKS\":\"ZA01\",\"LGORT\":\"G065\"}]}";
        return result;
    }
}
