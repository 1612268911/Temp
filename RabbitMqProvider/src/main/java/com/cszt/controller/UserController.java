package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author lilin
 * @create 2018/8/30 15:30
 * Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/sendTest")
    public String sendTest() throws Exception {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName("jjj");
        user.setPassWord("111");
        userService.send(user);
        return user.getUserId();
    }
}