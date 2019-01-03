package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lilin
 * @create 2018/12/26 10:40
 * description:
 */
@SpringBootApplication
@ComponentScan("com.cszt")
public class StartApp {
    public static void main(String[] args){
        SpringApplication.run(StartApp.class,args);
    }
}