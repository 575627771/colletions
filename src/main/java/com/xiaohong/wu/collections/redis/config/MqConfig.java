package com.xiaohong.wu.collections.redis.config;

import com.xiaohong.wu.collections.redis.delayqueue.DelayQueueSender;
import com.xiaohong.wu.collections.redis.delayqueue.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-11 下午10:20
 **/
@Configuration
public class MqConfig {

    @Bean
    public DelayQueueSender getDelayQueueSender(){
        DelayQueueSender delayQueueSender = new DelayQueueSender();
        delayQueueSender.setMonitorCount(30);
        delayQueueSender.setRoutes(getRoutes());
        return delayQueueSender;
    }

    private List<Route> getRoutes(){
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("queue:1","list:1"));
        routes.add(new Route("queue:2","list:2"));
        return routes;
    }
}
