package com.cszt.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author lilin
 * @create 2018/9/17 16:27
 * description: zookeeper 分布式锁
 */
public class DistributedLock implements Lock, Watcher {
    private String lockName = "/root";

    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch;

    private String currLock;

    private String waitLock;

    private static final String HOST = "localhost:2181";

    private static final Integer TIME_OUT = 2000;

    public DistributedLock() {
        try {
            zooKeeper = new ZooKeeper("localhost:2181", TIME_OUT, this);
            // 连接zookeeper
            Stat stat = zooKeeper.exists(lockName, false);
            if (stat == null) {
                // 如果根节点不存在，则创建根节点
                zooKeeper.create(lockName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        try {
            if (this.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " " + lockName + "获得了锁");
                return;
            } else {
                // 等待锁
                waitForLock(waitLock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            currLock = zooKeeper.create(lockName + "/node", "/node".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("创建节点成功--> " + currLock);
            List<String> nodeList = zooKeeper.getChildren(lockName, false);
            List<String> tempList = new ArrayList<String>();
            if (nodeList != null && nodeList.size() > 0) {
                for (String s : nodeList) {
                    String temp = "/root/" + s;
                    tempList.add(temp);
                }
                Collections.sort(tempList);
                String node = tempList.get(0);
                //当前节点为第一个节点时
                if (node.equals(currLock)) {
                    return true;
                }

                //不是第一节点时等待上一节点释放锁
                waitLock = tempList.get(Collections.binarySearch(tempList, currLock) - 1);
                return false;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitForLock(String node) {
        try {
            Stat stat = zooKeeper.exists(node, true);
            if (stat != null) {
                System.out.println(Thread.currentThread().getName() + "等待锁 " + node);
                this.countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
                countDownLatch = null;
                System.out.println(Thread.currentThread().getName() + "获得了锁...");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            System.out.println("释放锁 " + currLock);
            zooKeeper.delete(currLock, -1);
            currLock = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock();
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
            Thread.sleep(5000);
        }
    }
}