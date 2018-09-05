package com.cszt;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lilin
 * @create 2018/9/5 20:49
 * description:
 */
public class ParamScheduler {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("name","laosi");
        dataMap.put("age",32);
        dataMap.put("isJob",false);
        JobDetail jobDetail = JobBuilder
                .newJob(ParamJob.class)
                .setJobData(dataMap)
                .build();
        ScheduleBuilder builder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(builder)
                .forJob(jobDetail)
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}