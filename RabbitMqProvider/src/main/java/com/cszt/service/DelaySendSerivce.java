package com.cszt.service;

import com.cszt.queue.QueueMessageServiceSupport;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lilin
 * @create 2018/9/7 14:13
 * description: rabbitmq消息延迟消费
 */
@Service
public class DelaySendSerivce {
    @Autowired
    private QueueMessageServiceSupport queueMessageServiceSupport;

    public void sendMessage() throws Exception {
        String messageContent = "延迟发送消息-->发送时间："+new Date();
        queueMessageServiceSupport.sendDelayMessage(messageContent,"message.center.topic.ttl","message.center.create.ttl",10000);
        System.out.println("发送消息:"+messageContent);
    }
}