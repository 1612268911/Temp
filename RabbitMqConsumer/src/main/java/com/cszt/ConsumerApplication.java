package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lilin
 * @create 2018/8/30 15:37
 * Description:
 */
@SpringBootApplication
@ComponentScan(value = "com.cszt")
public class ConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class,args);
    }
}