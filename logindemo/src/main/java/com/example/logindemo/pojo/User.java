/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: User
 * Author:   jj
 * Date:     2018/5/12 13:44
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.logindemo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户实体类〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@ApiModel
public class User implements Serializable {
    @ApiModelProperty("名称")
    private int id;
    private String username;
    private String password;
    private Clazz clazz;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public Clazz getClazz(){
        return clazz;
    }
    public void setClazz(Clazz clazz){
        this.clazz = clazz;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
