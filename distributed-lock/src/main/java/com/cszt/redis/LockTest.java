package com.cszt.redis;

/**
 * @author lilin
 * @create 2018/9/27 14:32
 * description: redis分布式锁测试
 */
public class LockTest {

    private static volatile int add = 100;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    DistributedLock distributedLock = new DistributedLock();
                    if(distributedLock.lock()){
                        while(add>0){
                            System.out.println(Thread.currentThread().toString()+" ———————— add@"+add);
                            add--;
                            if(add==95){
                                break;
                            }
                            if(add==85){
                                break;
                            }
                            if(add==75){
                                break;
                            }
                            if(add==65){
                                break;
                            }
                            if(add==55){
                                break;
                            }
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                            }
                        }
                        distributedLock.unLock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i = 8;i>0;i--){
            new Thread(runnable).start();
            //Thread.sleep(2000);
        }
    }
}