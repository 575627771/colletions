package com.xiaohong.wu.collections.redis.delayqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-11 下午10:31
 **/
@Component
public class DelayQueueConsume {

    @Autowired
    private DelayQueueSender delayQueueSender;

    @Scheduled(cron = "*/5 * * * * *")
    public void consume() {
        System.out.println("-----------xiaofei定时任务开始------------");
        List<String> consume = delayQueueSender.consume(delayQueueSender.getRoutes().get(0).getList());
        consume.forEach(System.out::println);
        System.out.println("-----------xiaofei定时任务jieshu------------");

    }
}
