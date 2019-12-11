package com.xiaohong.wu.collections.redis.delayqueue;

import com.alibaba.fastjson.JSON;
import com.xiaohong.wu.collections.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 延时队列的发送者
 *
 * @author wu
 * @version 1.0
 * @date 19-1-9 下午10:46
 **/

public class DelayQueueSender {

    private static final String MSG_POOL = "Message:Pool:";

    private static final int DEFAUT_MONITOR = 10;

    private int monitorCount = DEFAUT_MONITOR;

    private List<Route> routes;

    @Autowired
    private RedisService redisService;

    /**
     * @param message
     * @return
     */
    public Boolean addMsgPool(Message message) {
        return redisService.set(MSG_POOL + message.getId(), message.getBody(), message.getTtl());
    }

    /**
     * @param id
     * @return
     */
    public Boolean delMsgPool(String id) {
        redisService.del(MSG_POOL + id);
        return true;
    }

    /**
     * @param key
     * @param score
     * @param val
     * @return
     */
    public Boolean zAdd(String key, long score, String val) {
        return redisService.zAdd(key, val, score);
    }

    /**
     * @param key
     * @param id
     * @return
     */
    public Boolean zDel(String key, String id) {
        return redisService.zDel(key, id);
    }

    public List<String> consume(String key) {
        Long size = redisService.lGetListSize(key);
        if (size != null && size > 0) {
            List<String> result = new ArrayList<>();
            List<Object> list = redisService.lGet(key, 0, size - 1);
            list.forEach(c -> {
                String s = (String) redisService.get(MSG_POOL + c.toString());
                System.out.println("consume:"+s+"===>c:"+c);
                result.add(s);
                redisService.lRemove(key,1,c);
            });
            return result;
        }
        return new ArrayList<>();
    }

    @Scheduled(cron="*/5 * * * * *")
    public void monitor() {
        System.out.println("--------路由定时任务开始---------");
        // 获取消息路由
        int route_size;
        if (null == routes || 1 > (route_size = routes.size())) {
            return;
        }
        String queue, list;
        Set<Object> set;
        for (int i = 0; i < route_size; i++) {
            queue = routes.get(i).getQueue();
            list = routes.get(i).getList();
            set = redisService.getSoredSetByRange(queue, 0, monitorCount, true);
            System.out.println("set:"+ JSON.toJSONString(set));
            if (null != set) {
                long current = System.currentTimeMillis();
                long score;
                for (Object id : set) {
                    score = redisService.getScore(queue, id.toString()).longValue();
                    System.out.println("score:"+score);
                    if (current >= score) {
                        // 添加到list
                        if (redisService.lSet(list, id.toString())) {
                            // 删除queue中的元素
                            zDel(queue, id.toString());
                        }
                    }
                }
            }
        }
        System.out.println("--------路由定时任务结束---------");
    }


    public int getMonitorCount() {
        return monitorCount;
    }

    public void setMonitorCount(int monitorCount) {
        this.monitorCount = monitorCount;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
