package com.cszt.service;

import com.cszt.domain.BasicGoods;
import com.cszt.jobs.InventoryCheckJob;
import com.cszt.jobs.PromptJob;
import com.cszt.repository.BasicGoodsMapper;
import com.sun.xml.internal.ws.developer.Serialization;
import org.quartz.*;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lilin
 * @create 2018/9/6 14:45
 * description:
 */
@Service
public class BasicGoodService {
    @Autowired
    private BasicGoodsMapper basicGoodsMapper;

    @Autowired
    private Scheduler scheduler;

    public boolean addGoods(BasicGoods goods) throws SchedulerException {
        boolean flag = basicGoodsMapper.addGoods(goods);
        //提示任务
        promptJob(goods.getId());
        //检查任务
        inventoryCheckJob();
        return flag;
    }
    /**
     * @author lilin
     * @description 添加成功 1分钟后提示
     * @date: 2018/9/6 15:44
     * @param:
     * @return:
     */
    public void promptJob(String id) throws SchedulerException {
        JobDataMap map = new JobDataMap();
        map.put("id",id);
        JobDetail jobDetail = JobBuilder
                .newJob(PromptJob.class)
                .storeDurably(true)
                .setJobData(map)
                .withIdentity("PromptJob","goodsJob")
                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date date = calendar.getTime();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("PromptJobTrigger","triggerJob")
                .startAt(date)
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }
    /**
     * @author lilin
     * @description 库存检查任务 30s检查一次
     * @date: 2018/9/6 15:48
     * @param:
     * @return:
     */
    public void inventoryCheckJob() throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                .newJob(InventoryCheckJob.class)
                .withIdentity("InventoryCheckJob","goodsJob")
                .storeDurably(true)
                .build();
        CronScheduleBuilder builder = CronScheduleBuilder
                .cronSchedule("0/5 * * * * ?");
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("InventoryCheckTrigger","triggerJob")
                .withSchedule(builder)
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}