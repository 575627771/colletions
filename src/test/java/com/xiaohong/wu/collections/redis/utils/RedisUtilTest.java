package com.xiaohong.wu.collections.redis.utils;

import com.xiaohong.wu.collections.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisService redisUtil;

    @Autowired
    private Jedis jedis;

    /*@Before
    public void init(){
        redisUtil = getRedisUtil();
    }

    public RedisUtil getRedisUtil(){
        return new RedisUtil();
    }*/

    @Test
    public void expire() {
//        redisUtil.expire()
    }

    @Test
    public void getExpire() {
    }

    @Test
    public void hasKey() {
    }

    @Test
    public void del() {
        redisUtil.del("test", "test1");
    }

    @Test
    public void get() {
        System.out.println(redisUtil.get("test"));
    }

    @Test
    public void set() {
        redisUtil.set("test1", "testee");
    }

    @Test
    public void setJedis() {
        redisUtil.setByJedis("test2", "testee");
    }

    @Test
    public void delJedis() {
        jedis.del("test2");
    }

    @Test
    public void millLock() {
        RedisLock lock = new RedisLock(jedis, "key", 200L);
        if (lock.millLock()) {
            System.out.println("加锁成功");
        } else {
            System.out.println("加锁失败");
        }
        long start = System.currentTimeMillis();
        System.out.println("第二次开始加锁时间===========》" + start);
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault()));
        while (!lock.millLock()) {
            System.out.println("加锁失败");
        }
        long end = System.currentTimeMillis();
        System.out.println("第二次结束加锁时间===========》" + (end - start));
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault()));
    }

    @Test
    public void secondsLock() {
        RedisLock lock = new RedisLock(jedis, "k", 20L);
        if (lock.secondsLock()) {
            System.out.println("加锁成功---》");
        } else {
            System.out.println("加锁失败---》");
        }
        long start = System.currentTimeMillis();
        System.out.println("第二次开始加锁时间===========》" + start);
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault()));
        while (!lock.secondsLock()) {
            System.out.println("加锁失败");
        }
        long end = System.currentTimeMillis();
        System.out.println("第二次结束加锁时间===========》" + (end - start));
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault()));
    }

    @Test
    public void tryLock() {
        RedisLock lock = new RedisLock(jedis, "k", 2L);
        lock.secondsLock();
        long start = System.currentTimeMillis();
        System.out.println("第二次开始加锁时间===========》" + start);
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault()));
        if (lock.tryLock()) {
            System.out.println("加锁成功---》");
        } else {
            System.out.println("加锁失败---》");
        }
        long end = System.currentTimeMillis();
        System.out.println("第二次结束加锁时间===========》" + (end - start));
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault()));
    }
}