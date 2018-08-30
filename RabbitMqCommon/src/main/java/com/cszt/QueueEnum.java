package com.cszt;

import lombok.Getter;

/**
 * @author lilin
 * @create 2018/8/30 15:16
 * Description: 队列配置枚举
 */
@Getter
public enum QueueEnum
{
    /**
     * 用户注册枚举
     */
    USER_REGISTER("user.register.queue","user.register")
    ;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 队列路由键
     */
    private String routingKey;

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }
}