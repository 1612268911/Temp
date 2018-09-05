package com.cszt;

/**
 * @author lilin
 * @create 2018/9/5 14:46
 * description:
 */
import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootStarpA {
    private static Logger logger = LoggerFactory.getLogger(BootStarpA.class);

    public static void main(String[] args) {

        try {
            // 获取Scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // 具体任务
            JobDetail job = JobBuilder.newJob(BootStarpJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronScheduleBuilder)
                    .build();

            // 交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);

            try {
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 关闭Scheduler
            scheduler.shutdown();

        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        }
    }

}