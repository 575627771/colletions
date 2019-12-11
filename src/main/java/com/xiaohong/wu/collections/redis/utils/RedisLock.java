package com.xiaohong.wu.collections.redis.utils;

import lombok.Data;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author wu
 * @version 1.0
 * @date 18-12-27 下午11:25
 */
@Data
public class RedisLock {

    private static final String NX = "NX";
    private static final String PX = "PX";
    private static final String EX = "EX";
    private static final String OK = "OK";
    private static final String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
            "then return redis.call(\"del\",KEYS[1]) else return 0 end ";
    /**
     * 这里把sleep nano参数定为常量，源码可知
     */
    public static final int BOUND = 1000000;
    private final Jedis jedis;
    private final Random random;
    private String key;
    private String requestId;
    private Long expireTime;
    private Long timeOut;
    private volatile boolean locked;

    public RedisLock(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
        this.expireTime = 10L;
        this.timeOut = 500L;
        this.locked = false;
        this.random = new Random();

    }

    public RedisLock(Jedis jedis, String key, Long expireTime) {
        this(jedis, key);
        this.expireTime = expireTime;
    }

    public RedisLock(Jedis jedis, String key, Long expireTime, Long timeOut) {
        this(jedis, key, expireTime);
        this.timeOut = timeOut;
    }

    public boolean millLock() {
        this.requestId = UUID.randomUUID().toString();
        String result = jedis.set(key, requestId, NX, PX, expireTime);
        return locked = OK.equalsIgnoreCase(result);
    }

    public boolean secondsLock() {
        this.requestId = UUID.randomUUID().toString();
        String result = jedis.set(key, requestId, NX, EX, expireTime);
        return locked = OK.equalsIgnoreCase(result);
    }

    /**
     * tryLock 在一定时间范围内尝试加锁  这里默认使用秒级锁 默认过期时间是10秒 默认加锁时间500毫秒
     * EX = seconds; PX = milliseconds
     * 1秒=1000毫秒=1000,00,0000纳秒
     *
     * @return
     */
    public boolean tryLock() {
        this.requestId = UUID.randomUUID().toString();
        long startTime = System.nanoTime();
        //把加锁时间转换成纳秒
        long outTime = timeOut * 1000000L;

        while (System.nanoTime() - startTime < outTime) {
            System.out.println("expire====>" + jedis.pttl(key));
            if (OK.equalsIgnoreCase(jedis.set(key, requestId, NX, EX, expireTime))) {
                return this.locked = true;
            }
            rSleep(timeOut / 10);
        }
        return this.locked;
    }

    /**
     * 查看锁状态
     *
     * @return
     */
    public boolean isLock() {
        return this.locked;
    }

    /**
     * 阻塞加锁，直到加锁成功为止
     *
     * @return
     */
    public boolean blockLock() {
        this.requestId = UUID.randomUUID().toString();

        while (true) {
            if (OK.equalsIgnoreCase(jedis.set(key, requestId, NX, EX, expireTime))) {
                return this.locked = true;
            }
            rSleep(timeOut / 10);
        }
    }

    /**
     * 解锁
     *
     * @return
     */
    public boolean unLock() {
        if (this.locked) {
            try {
                List<String> keys = new ArrayList<>();
                keys.add(this.key);
                List<String> values = new ArrayList<>();
                values.add(this.requestId);
                Long result = (Long) jedis.eval(UNLOCK_LUA, keys, values);
                return locked = result != 0L;
            } catch (Throwable e) {
                String value = jedis.get(key);
                if (requestId.equals(value)) {
                    return locked = jedis.del(key) != 0L;
                }
                return true;
            }
        }
        return true;
    }

    private void rSleep(long millis) {
        try {
            System.out.println("millis===>" + millis + "nanos======>" + BOUND);
            //这个方法其实并没有实现纳秒级的睡眠
            Thread.sleep(millis, random.nextInt(BOUND));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
