package com.cszt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cszt.domain.User;
import com.cszt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilin
 * @create 2018/9/11 11:11
 * description:
 */
@Slf4j
@RestController
public class UserController {
    @Reference(version = "1.0.0")
    private UserService userService;
    @GetMapping("/getUser")
    public User getUser(){
        User user = userService.getUser();
        log.info("查询用户：{}",user);
        return user;
    }
}