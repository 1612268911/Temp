package com.cszt.queue;

import com.cszt.ExchangeEnum;
import com.cszt.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author lilin
 * @create 2018/8/30 15:11
 * Description:
 */
@Component
public class QueueMessageServiceSupport
        implements QueueMessageService
{
    /**
     * 消息队列模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Object message, ExchangeEnum exchangeEnum, String routeKey) throws Exception {
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchangeEnum.getName(),routeKey,message);
    }
}