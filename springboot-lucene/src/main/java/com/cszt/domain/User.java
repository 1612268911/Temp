package com.cszt.domain;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/11/19 14:57
 * description:
 */
public class User implements Serializable {
    private int id;

    private String name;

    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}