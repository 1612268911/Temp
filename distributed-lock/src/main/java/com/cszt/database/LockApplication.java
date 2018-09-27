package com.cszt.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lilin
 * @create 2018/9/27 21:09
 * description:
 */
@SpringBootApplication
@MapperScan("com.cszt.database")
public class LockApplication {
    public static void main(String[] args){
        SpringApplication.run(LockApplication.class,args);
    }
}