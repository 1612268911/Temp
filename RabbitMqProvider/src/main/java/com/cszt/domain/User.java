package com.cszt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/8/30 15:05
 * Description: 用户实体
 */
@Data
public class User implements Serializable{
    private String userId;

    private String userName;

    private String passWord;
}