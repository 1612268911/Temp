package com.cszt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lilin
 * @create 2018/9/7 14:38
 * description: rabbitmq消息延迟消费配置
 */
@Slf4j
@Configuration
public class DelaySendConfiguration {
    @Bean
    public Queue queue(){
        return QueueBuilder
                .durable("message.center.queue")
                .build();
    }
    @Bean
    public DirectExchange exchange(){
        return (DirectExchange)ExchangeBuilder.directExchange("message.center.exchange")
                                            .durable(true)
                                            .build();
    }
    @Bean
    public Binding binding(DirectExchange exchange,Queue queue){
        log.info("message.center.queue 绑定完成...");
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("message.center.routkey");
    }
    @Bean
    public Queue delayQueue(){
        return QueueBuilder.durable("message.center.create.ttl")
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", "message.center.exchange")
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", "message.center.routkey")
                .build();
    }
    @Bean
    public DirectExchange directExchange(){

        return (DirectExchange)ExchangeBuilder.directExchange("message.center.topic.ttl")
                .durable(true)
                .build();
    }
    @Bean
    public Binding delayBinding(DirectExchange directExchange,Queue delayQueue){
        log.info("message.center.create.ttl 绑定完成...");
        return BindingBuilder
                .bind(delayQueue)
                .to(directExchange)
                .with("message.center.create.ttl");
    }
}