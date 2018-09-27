package com.cszt.database;

/**
 * @author lilin
 * @create 2018/9/27 20:52
 * description:
 */
public class TimeOutTask implements Runnable {

    private DistributedLock distributedLock;

    private int invailData;

    public TimeOutTask(DistributedLock distributedLock,int invailData){
        this.distributedLock = distributedLock;
        this.invailData = invailData;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(invailData*1000);
            distributedLock.unLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}