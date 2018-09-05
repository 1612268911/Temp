package com.cszt;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lilin
 * @create 2018/9/3 16:44
 * Description: quartz定时任务测试
 */
public class SimpleTriggerTest {
    Scheduler scheduled = StdSchedulerFactory.getDefaultScheduler();

    public SimpleTriggerTest() throws SchedulerException {

    }
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        //定义任务器
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")
                .build();
        //定义触发器
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1","group1")
//                .withSchedule(
//                        SimpleScheduleBuilder
//                                .simpleSchedule()
//                                .withIntervalInSeconds(1)//1秒循环一次
//                                .repeatForever())//一直重复
//                .startNow()
//                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);
        Date date = calendar.getTime();
        SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(date)                   //开始执行时间
                .withSchedule(SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(2)//2秒一次循环\
                                .withRepeatCount(3)//重复次数
                                )
                .forJob("job1", "group1")                 // identify job with name, group strings
                .build();

        //注册任务器和触发器到scheduler中
        scheduler.scheduleJob(jobDetail,trigger);
        Thread.sleep(22000);
        scheduler.shutdown();
    }
}