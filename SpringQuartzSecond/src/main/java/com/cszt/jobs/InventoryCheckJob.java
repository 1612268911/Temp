package com.cszt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author lilin
 * @create 2018/9/6 15:01
 * description: 库存检查定时任务
 */
public class InventoryCheckJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("库存检查任务 执行时间-->"+new Date());
    }
}