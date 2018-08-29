package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilin
 * @create 2018/8/29 17:12
 * Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public String userRegister(@RequestBody User user){
        userService.UserRegister(user);
        return "register success!";
    }
}