package com.szzt.smart.demo.service;

import com.sun.xml.internal.ws.client.sei.ValueSetter;
import com.szzt.smart.demo.entity.User;
import com.szzt.smart.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-06-08
 */
@Service
public class UserService
{

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUser(Integer id){
        String key = id+"";
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        ListOperations operationslist = redisTemplate.opsForList();
        operationslist.leftPush("jk",new ArrayList<>());
        List list = operationslist.range("jk",0,1000);
        boolean hasKey = redisTemplate.hasKey(key);
        //缓存存在
        if(hasKey){
            User user = operations.get(key);
            logger.info("从缓存中获取了用户 -- " +"key="+ key + "->" + user);
            return user;
        }
        //从数据库获取
        User user = mapper.getUser(id);
        logger.info("从数据库获取了用户 >> " +"key="+ key + "->" + user);
        // 插入缓存
        operations.set(key,user);
        return user;
    }
    public String test(Integer id){
        String key = ""+id;
        Jedis jedis = new Jedis("localhost");
        jedis.ping();

        boolean flag = jedis.exists(key);
        if(flag){
            long count = jedis.incr(key);//自增
            logger.info("jedis count === "+count);
            if(count!=1){
                return "每分钟只能访问一次....";
            }
            String result = jedis.get(key);
            logger.info("从缓存中获取了用户 -- " +"key="+ key + "->" + result);
            return result;
        }
        String result = (int)(Math.random()*100)+"";
        jedis.set(key,result);
        jedis.expire(key,30);//有效时间
        logger.info("自动生成数据 >> " +"key="+ key + "->" + result);
        return result;
    }
    //计数器
    public User getUserTest(Integer id){
        ValueOperations<String,User> result = redisTemplate.opsForValue();
        String key = "count_"+id;
        /*
        Redis Incr 命令将 key 中储存的数字值增一。
        如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
        */
        long count = result.increment(key,1);//每次增加 1
        logger.info("count = "+count);
        if(count==1){
            redisTemplate.expire(key,60, TimeUnit.SECONDS);//有效时间
        }
        if(count>1){
            User user = new User();
            user.setName("每分钟只能请求一次....");
            return user;
        }
        return mapper.getUser(id);
    }
}
