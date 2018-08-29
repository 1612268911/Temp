package com.cszt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/8/28 20:06
 * Description: Token实体
 */
@Data
public class TokenResult implements Serializable {
    private String  tokenId;

    private String token;

    private Integer userId;
    /**
     * 无效时间
     */
    private long invailDate;
}