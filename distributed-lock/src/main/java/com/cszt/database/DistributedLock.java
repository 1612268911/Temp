package com.cszt.database;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;

/**
 * @author lilin
 * @create 2018/9/27 20:40
 * description:
 */
@Service
public class DistributedLock {

    @Autowired
    LockMapper lockMapper;

    private static final int INVIL_DATE = 30;

    private static final String LOCK_NAME = "lockName";

    private boolean flag = false;

    private Lock lock;
    /**
     * @author lilin
     * @description 获取锁
     * @date: 2018/9/27 20:50
     * @param:
     * @return:
     */
    public boolean lock(){
        long date = System.currentTimeMillis()+INVIL_DATE*1000;
        while(System.currentTimeMillis()<date){
            String lockValue = lockMapper.check(LOCK_NAME);
            System.out.println("lockValue---->"+lockValue);
            if(lockValue == null || lockValue.equals("")){
                try{
                    System.out.println(Thread.currentThread().toString()+"获取锁start....");
                    lockMapper.save(LOCK_NAME);
                    System.out.println(Thread.currentThread().toString()+"获取锁end....");
                    flag = true;
                    //设置有效时间
                    //new Thread(new TimeOutTask(this,INVIL_DATE)).start();
                    return flag;
                }catch (Exception e){
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee----->");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    /**
     * @author lilin
     * @description 释放锁
     * @date: 2018/9/27 20:50
     * @param:
     * @return:
     */
    public void unLock(){
        if(flag){
            lockMapper.del(LOCK_NAME);
            System.out.println(Thread.currentThread().toString()+"释放锁...");
        }
    }
}