package com.cszt;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author lilin
 * @create 2018/9/5 9:52
 * description:
 */
public class CronTriggerTest {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")
                .build();
        ScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");//从0开始，每5秒执行一次
        CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
                .withSchedule(builder)
                .build();
        scheduler.scheduleJob(jobDetail,trigger);

    }
}