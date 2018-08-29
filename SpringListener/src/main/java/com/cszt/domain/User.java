package com.cszt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/8/29 17:01
 * Description:
 */
@Data
public class User implements Serializable {

    private String userName;

    private String passWord;
}