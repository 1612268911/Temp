package com.cszt.queue;

import com.cszt.ExchangeEnum;
import com.cszt.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author lilin
 * @create 2018/8/30 15:08
 * Description:
 */
public interface QueueMessageService{
    /**
     * 发送消息到rabbitmq消息队列
     * @param message 消息内容
     * @param exchangeEnum 交换配置枚举
     * @param
     * @throws Exception
     */
    public void send(Object message, ExchangeEnum exchangeEnum, String routeKey) throws Exception;
}
