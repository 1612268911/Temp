package com.szzt.smart.testlist.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-05-22
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 学生编号
     */
    private String sid;
    /**
     * 姓名
     */
    private String name;

    /**
     * 分数
     * @return
     */
    private Score score;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
