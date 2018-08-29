package com.cszt;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lilin
 * @create 2018/8/29 17:15
 * Description: UserRegisterEvent事件监听器
 */
@Component
public class UserRegisterListener {

    @EventListener
    public void register(UserRegisterEvent userRegisterEvent){
        User user = userRegisterEvent.getUser();
        Object obj = userRegisterEvent.getSource();
        System.out.println(obj);
        System.out.println("@UserRegisterEvent注册用户：userName: "+user.getUserName()+"  passWord: "+user.getPassWord());
        sendEmail();
    }
    public void sendEmail(){
        System.out.println("发送邮件成功...");
    }
}