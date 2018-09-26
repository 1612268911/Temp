package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lilin
 * @create 2018/9/25 11:08
 * description:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public String insert(){
        userService.insert();
        return "success!";
    }
    @GetMapping("/findAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @GetMapping("/find")
    public List<User> getUser(String name) {
        return userService.getUser(name);
    }
}