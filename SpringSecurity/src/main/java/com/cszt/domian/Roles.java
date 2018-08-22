/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Roles
 * Author:   jj
 * Date:     2018/8/14 16:09
 * Description: 用户权限
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domian;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户权限〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
public class Roles implements Serializable{

    private Integer rId;

    private String rName;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}