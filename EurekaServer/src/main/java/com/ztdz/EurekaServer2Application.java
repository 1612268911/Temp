package com.ztdz;
/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EurekaServer2Application
 * Author:   jj
 * Date:     2018/5/11 17:29
 * Description: eurekaserver启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈eurekaserver启动类〉
 *
 * @author jj
 * @create 2018/5/11
 * @since 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages = {"com.ztdz"})
public class EurekaServer2Application {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(EurekaServer2Application.class,args);
    }
}
