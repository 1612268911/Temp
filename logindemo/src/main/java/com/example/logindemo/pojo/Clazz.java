/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Clazz
 * Author:   jj
 * Date:     2018/5/14 10:24
 * Description: 用户班级信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.logindemo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户班级信息〉
 *
 * @author jj
 * @create 2018/5/14
 * @since 1.0.0
 */
public class Clazz implements Serializable {
    private int cid;
    private String cname;
    private Date cdate;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cdate=" + cdate +
                '}';
    }
}
