package com.cszt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lilin
 * @create 2018/8/30 15:57
 * Description: 配置路由交换对象实例
 */
@Slf4j
@Configuration
public class UserRegisterQueueConfiguration {
    /**
     * 创建交换机（一旦创建就不能改变）
     * TopicExchange、DirectExchange、FanoutExchange
     */
    @Bean
    public TopicExchange userTopicExchange(){
        log.info("交换机创建成功...");
        return new TopicExchange(ExchangeEnum.USER_REGISTER.getName());
    }

    /**
     * 配置用户注册队列对象实例
     * 并设置持久化队列
     * @return
     */
    @Bean
    public Queue userRegisterQueue(){
        log.info("用户注册队列创建成功...");
        return new Queue(QueueEnum.USER_REGISTER.getName(),true);
    }
    /**
     * 将用户注册队列绑定到路由交换配置上并设置指定路由键进行转发
     * @return
     */
    @Bean
    public Binding userRegisterBinding(TopicExchange userTopicExchange,Queue userRegisterQueue){
        log.info("用户注册队列与交换机绑定成功...");
        return BindingBuilder.bind(userRegisterQueue).to(userTopicExchange).with("");
    }
    /**
     * @Author lilin
     * @Description 创建发送邮件队列
     * @Date: 2018/8/31 15:46
     * @param:
     * @return:
     */
    @Bean
    public Queue sendMailQueue(){
        log.info("发送邮件队列创建成功...");
        return new Queue(QueueEnum.SEND_MAIL.getName(),true);
    }
    /**
     * @Author lilin
     * @Description 邮件队列与交换机绑定，并设置指定路由键转发
     * @Date: 15:47
     * @param:
     * @return:
     */
    @Bean
    public Binding sendMailBinding(TopicExchange userTopicExchange,Queue sendMailQueue){
        log.info("邮件队列与交换机绑定成功....");
        return BindingBuilder.bind(sendMailQueue).to(userTopicExchange).with("");
    }
}