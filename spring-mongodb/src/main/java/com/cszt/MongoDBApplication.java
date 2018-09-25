package com.cszt;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lilin
 * @create 2018/9/25 11:00
 * description:
 */
@SpringBootApplication
public class MongoDBApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args){
        SpringApplication.run(MongoDBApplication.class,args);
    }
}