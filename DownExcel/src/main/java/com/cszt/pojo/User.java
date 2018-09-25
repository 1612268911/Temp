/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: domain
 * Author:   jj
 * Date:     2018/5/12 13:44
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.pojo;


import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户实体类〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
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
