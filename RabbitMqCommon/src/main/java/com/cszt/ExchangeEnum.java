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
    USER_REGISTER("user.register.topic.exchange");

    private String value;

    ExchangeEnum(String value) {
        this.value = value;
    }
}