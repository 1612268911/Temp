package com.cszt;

/**
 * @author lilin
 * @create 2018/9/5 14:58
 * description:
 */
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BootStarpB {
    private static Logger logger = LoggerFactory.getLogger(BootStarpB.class);

    public static void main(String[] args) {

        try {
            // 获取Scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        }
    }
}