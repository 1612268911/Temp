package com.cszt.config;

/**
 * @author lilin
 * @create 2018/9/23 20:51
 * description:
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisConfig.setJedisConnectionFactory(jedisConnectionFactory);
    }
}