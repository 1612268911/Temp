/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: domain
 * Author:   jj
 * Date:     2018/8/14 15:57
 * Description: 用户实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domain;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户实体〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
public class User{
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "domain{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}