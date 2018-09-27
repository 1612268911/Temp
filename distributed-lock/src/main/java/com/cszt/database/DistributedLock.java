package com.cszt.database;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

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
    /**
     * @author lilin
     * @description 获取锁
     * @date: 2018/9/27 20:50
     * @param:
     * @return:
     */
    public synchronized boolean lock(){
        long date = System.currentTimeMillis()+INVIL_DATE*1000;
        while(System.currentTimeMillis()<date){
            String lock = lockMapper.check(LOCK_NAME);
            System.out.println("lock---->"+lock);
            if(lock == null || lock.equals("")){
                try{
                    lockMapper.save(LOCK_NAME);
                    flag = true;
                    //设置有效时间
                    new Thread(new TimeOutTask(this,INVIL_DATE)).start();
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
        }
    }
}