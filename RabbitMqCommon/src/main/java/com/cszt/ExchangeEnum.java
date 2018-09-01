package com.cszt;

import lombok.Getter;

/**
 * @author lilin
 * @create 2018/8/30 15:14
 * Description: Rabbit交换配置枚举
 */
@Getter
public enum ExchangeEnum
{
    /**
     * 用户注册交换配置枚举
     */
    USER_REGISTER("register.topic.exchange");

    private String name;

    ExchangeEnum(String name) {
        this.name = name;
    }
}