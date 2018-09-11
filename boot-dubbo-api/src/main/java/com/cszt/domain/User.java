package com.cszt.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/9/10 17:16
 * description:
 */
@Data
@Builder
public class User implements Serializable {
    private String userId;

    private String userName;

    private Integer age;
}