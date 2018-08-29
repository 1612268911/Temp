package com.cszt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/8/28 20:08
 * Description: 用户实体
 */
@Data
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String passWord;
}