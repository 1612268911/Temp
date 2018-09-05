package com.cszt;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lilin
 * @create 2018/9/3 16:54
 * Description:
 */
public class BootStarpJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("bootstarp job ....");
    }
}