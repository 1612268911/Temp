package com.cszt.config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lilin
 * @create 2018/12/27 15:34
 * description:
 */
public class TheadTest{

    private int score=16;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock wLock = lock.writeLock();
    Lock rLock = lock.readLock();

    public void remove(){
        score--;
    }
    public void add(){
        score++;
    }

    public int getScore() {
        return score;
    }

}