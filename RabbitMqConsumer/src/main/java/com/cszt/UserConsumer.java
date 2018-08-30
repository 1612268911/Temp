package com.cszt;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lilin
 * @create 2018/8/30 15:39
 * Description:
 */
@Component
@RabbitListener(queues = "user.register.queue")
public class UserConsumer {
    @RabbitHandler
    public void execute(String userId){
        System.out.println("注册成功：userId="+userId);
    }
}