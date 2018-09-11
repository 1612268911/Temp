package com.cszt.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.cszt.domain.User;

import java.util.UUID;

/**
 * @author lilin
 * @create 2018/9/10 17:17
 * description:
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(){
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .userName("老大")
                .age(23)
                .build();
        return user;
    }
}