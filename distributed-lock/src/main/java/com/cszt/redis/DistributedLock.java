package com.cszt.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author lilin
 * @create 2018/9/27 11:17
 * description: redis实现分布式锁
 */
public class DistributedLock {

    private static final String lockName = "LOCA_NAME";
    
    private String lockValue = "LOCKED";

    private static final int invailDate = 30;

    private boolean flag = false;

    private Jedis jedis;
    /**
     * @author lilin
     * @description 获取锁
     * @date: 2018/9/27 15:25
     * @param:
     * @return:
     */
    public boolean lock() throws InterruptedException {
        jedis = new Jedis("127.0.0.1",6379,60000);
        long overDate = System.currentTimeMillis()+invailDate*1000;
        while(System.currentTimeMillis()<overDate){
            jedis.watch(lockName);
            String value = jedis.get(lockName);
            System.out.println("value-->"+value);
            //开启watch之后，如果key的值被修改，则事务失败，exec方法返回null
            if(value == null || value.equals("UNLOCK")){
                Transaction multi = jedis.multi();
                multi.setex(lockName,invailDate,lockValue);
                List<Object> exec = multi.exec();
                if(exec!=null && exec.size()>0){
                    flag = true;
                    return flag;
                }
            }
            jedis.unwatch();
            Thread.sleep(3000);
        }
        return flag;
    }
    /**
     * @author lilin
     * @description 释放锁
     * @date: 2018/9/27 15:25
     * @param:
     * @return:
     */
    public void unLock(){
        if(flag){
            jedis.del(lockName);
            flag = false;
        }
    }
    public boolean getFlag(){
        return flag;
    }
}