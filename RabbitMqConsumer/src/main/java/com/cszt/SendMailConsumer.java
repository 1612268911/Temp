package com.cszt;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lilin
 * @create 2018/8/31 15:52
 * Description: 发送邮件接收消息客户端
 */
@Component
@RabbitListener(queues = "user.sendmail")
public class SendMailConsumer {
    @RabbitHandler
    public void execute(String userId){
        System.out.println("注册用户："+userId+"发送邮件成功...");
    }
}