/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RedisConfig
 * Author:   jj
 * Date:     2018/8/16 16:38
 * Description: redis配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.config;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈redis配置〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
public class RedisConfig implements Cache{

    private static JedisConnectionFactory jedisConnectionFactory;
    private final static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisConfig(final String id) {
        logger.info("====================MybatisRedisCache=====================");
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public void clear() {
        logger.info("====================clear=====================");
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    @Override
    public String getId() {
        logger.info("====================getId=====================");
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
        logger.info("====================getObject=====================");
        Object result = null;
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<String> serializer = new StringRedisSerializer();
            RedisSerializer<Object> serializer2 = new JdkSerializationRedisSerializer();
            result = serializer2.deserialize(connection.get(serializer.serialize(key.toString())));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public int getSize() {
        logger.info("====================getSize=====================");
        int result = 0;
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value) {
        logger.info("====================putObject=====================");
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<String> serializer = new StringRedisSerializer();
            RedisSerializer<Object> serializer2 = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key.toString()), serializer2.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Object removeObject(Object key) {
        logger.info("====================removeObject=====================");
        RedisConnection connection = null;
        Object result = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<String> serializer = new StringRedisSerializer();
            result = connection.expire(serializer.serialize(key.toString()), 0);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        RedisConfig.jedisConnectionFactory = jedisConnectionFactory;
    }
}