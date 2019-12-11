package com.xiaohong.wu.collections.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaohong.wu.collections.redis.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wu
 * @version 1.0
 * @date 18-12-27 下午9:09
 **/
@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisPool getJediPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxActive());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());

        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeOut(), redisProperties.getPassWord());
    }

    @Bean
    public Jedis getJedis(){
        return getJediPool().getResource();
    }


    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        //定义redis序列化工具
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //采用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        //设置那些可以进行序列化、以及什么修饰的可以序列化
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(redisSerializer);
        //初始化，加载配置后执行
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, Object> getValueOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForValue();
    }

    @Bean
    public HashOperations<String, Object, Object> getHashOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForHash();
    }

    @Bean
    public ListOperations<String, Object> getListOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForList();
    }

    @Bean
    public SetOperations<String, Object> getSetOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForSet();
    }

    @Bean
    public GeoOperations<String, Object> getGeoOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForGeo();
    }

    @Bean
    public HyperLogLogOperations<String, Object> getHyperLogLogOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForHyperLogLog();
    }

    @Bean
    public ZSetOperations<String, Object> getZSetOperations(RedisConnectionFactory connectionFactory) {
        return getRedisTemplate(connectionFactory).opsForZSet();
    }
}
