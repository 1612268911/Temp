package com.cszt;

import lombok.Getter;

/**
 * @author lilin
 * @create 2018/8/31 15:38
 * Description: routekey枚举
 */
@Getter
public enum  RouteKeyEnum {

    USER_ROUTE_KEY("user.register");

    private String value;

    RouteKeyEnum(String value){
        this.value = value;
    }
}