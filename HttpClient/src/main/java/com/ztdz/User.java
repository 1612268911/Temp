package com.ztdz;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

   private String name;

   private String pwd;

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

    @Override
    public String toString() {
        return "domain{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
