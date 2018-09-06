package com.cszt;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lilin
 * @create 2018/9/6 14:31
 * description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cszt.mapper"})
@ComponentScan(basePackages = {"com.cszt"})
public class QuartzApplication {
    public static void main(String[] args){
        SpringApplication.run(QuartzApplication.class,args);
    }
    @Bean
    public Scheduler scheduler() throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();//开始任务
        return scheduler;
    }
}