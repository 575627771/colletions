package com.xiaohong.wu.collections.redis.utils;

import com.xiaohong.wu.collections.redis.delayqueue.DelayQueueSender;
import com.xiaohong.wu.collections.redis.delayqueue.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-11 下午11:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    @Autowired
    private DelayQueueSender sender;

    @Test
    public void test(){
        Message message = new Message();
        message.setBody("hahahaha");
        message.setCreateTime(System.currentTimeMillis());
        message.setDelay(1000*60*30);
        message.setId(UUID.randomUUID().toString());
        message.setPriority(0);
        message.setStatus(0);
        message.setTopic("SMS");
        message.setTtl(60*60);
        sender.addMsgPool(message);
        sender.zAdd("queue:1",message.getCreateTime()+message.getDelay()+message.getPriority(),message.getId());
    }
}
