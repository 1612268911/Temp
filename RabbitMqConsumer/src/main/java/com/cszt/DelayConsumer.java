package com.cszt;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lilin
 * @create 2018/9/7 15:00
 * description: rabbitmq消息延迟消费
 */
@Component
@RabbitListener(queues = "message.center.queue")
public class DelayConsumer {
    @RabbitHandler
    public void execute(String messageContent){
        System.out.println("delay接收消息-->"+messageContent);
        System.out.println("delay接收时间："+new Date());
    }
}