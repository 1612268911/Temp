package com.cszt.domain;

import com.cszt.elasticsearch.CommonConfig;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lilin
 * @create 2018/11/19 14:57
 * description:
 */
@Document(indexName = CommonConfig.INDEX,type = CommonConfig.TYPE)
public class User implements Serializable {
    @Id
    private int id;

    private String name;

    private String pwd;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public User() {

    }

    public User(int id, String name, String pwd, Date createTime) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}