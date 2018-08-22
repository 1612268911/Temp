/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Mergn
 * Author:   jj
 * Date:     2018/7/30 20:34
 * Description: 学生与成绩合并对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.NewTest.entity;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈学生与成绩合并对象〉
 *
 * @author jj
 * @create 2018/7/30
 * @since 1.0.0
 */
public class Mergn implements Serializable{
    /**
     * 学生编号
     */
    private String sid;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学上成绩
     */
//    private Score score;

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

//    public Score getScore() {
//        return score;
//    }
//
//    public void setScore(Score score) {
//        this.score = score;
//    }
}