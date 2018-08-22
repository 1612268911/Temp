/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TimingTask
 * Author:   jj
 * Date:     2018/8/16 17:05
 * Description: 定时任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.jobs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈定时任务〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@Configuration
public class TimingTask {
    @Scheduled(cron = "0/10 * * * * ? ")//每10秒执行一次
    public void dowork(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = df.format(date);
        System.out.println("dateStr="+dateStr);
    }
}