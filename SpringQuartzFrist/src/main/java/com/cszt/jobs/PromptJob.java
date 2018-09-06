package com.cszt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lilin
 * @create 2018/9/6 14:57
 * description: 商品添加成功后提示任务
 */
public class PromptJob implements Job {

    private String id;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("商品添加成功...goodsId="+id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}