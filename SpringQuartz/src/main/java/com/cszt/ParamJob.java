package com.cszt;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lilin
 * @create 2018/9/5 20:46
 * description: 传参job
 */
public class ParamJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = map.getString("name");
        int age = map.getInt("age");
        boolean isJob = map.getBoolean("isJob");
        System.out.println("name="+name+"  age="+age+"  isJob="+isJob);
    }
}