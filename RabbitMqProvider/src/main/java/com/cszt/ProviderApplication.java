package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lilin
 * @create 2018/8/30 14:59
 * Description:
 */
@SpringBootApplication
@ComponentScan(value = "com.cszt")
public class ProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(ProviderApplication.class,args);
    }
}