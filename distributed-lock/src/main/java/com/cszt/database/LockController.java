package com.cszt.database;

import com.cszt.database.DistributedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lilin
 * @create 2018/9/27 21:04
 * description:
 */
@Controller
public class LockController {

    private static volatile int add = 100;
    @Autowired
    DistributedLock distributedLock;

    @GetMapping("/test")
    public String test() {
        testData();
        return "success";
    }

    public void testData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (distributedLock.lock()) {
                        while (add > 0) {
                            System.out.println(Thread.currentThread().toString() + " ———————— add@" + add);
                            add--;
                            if(add==90 || add == 95 || add == 85){
                                break;
                            }
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                            }
                        }
                        distributedLock.unLock();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 5; i > 0; i--) {
            System.out.println("i--->"+i);
            new Thread(runnable).start();
            //Thread.sleep(2000);
        }
    }
}