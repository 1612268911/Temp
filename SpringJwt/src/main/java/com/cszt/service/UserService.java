package com.cszt.service;

import com.cszt.domain.TokenResult;
import com.cszt.domain.User;
import com.cszt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lilin
 * @create 2018/8/28 20:29
 * Description:
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String userName,String passWord){
        return userMapper.getUser(userName,passWord);
    }
}