/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ApplicationBpm
 * Author:   jj
 * Date:     2018/7/23 20:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/23
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.cszt")
public class ApplicationBpm {
    @Bean
    public ProcessEngine processEngine(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        return  processEngine;
    }

    public static void main(String[] args){
        SpringApplication.run(ApplicationBpm.class,args);
    }
}