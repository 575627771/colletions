package com.xiaohong.wu.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-1 下午3:08
 **/
@RestController
@RequestMapping("/")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("log")
    public String logTest() {
        LOGGER.info("-------------->日志开始");
        LOGGER.info("-------------->日志中");
        LOGGER.info("-------------->日志结束");
        return "test";
    }
}
