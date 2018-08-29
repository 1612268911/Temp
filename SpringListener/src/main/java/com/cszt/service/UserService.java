package com.cszt.service;

import com.cszt.ListenerApplication;
import com.cszt.UserRegisterEvent;

import com.cszt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author lilin
 * @create 2018/8/29 17:07
 * Description:
 */
@Service
public class UserService {
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * @Author lilin
     * @Description 用户注册方法
     * @Date: 2018/8/29 17:12
     * @param:
     * @return:
     */
    public void UserRegister(User user){
        //发布UserRegisterEvent事件
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
    }
}