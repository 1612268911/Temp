/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Msg
 * Author:   jj
 * Date:     2018/8/15 9:19
 * Description: 消息实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domian;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息实体〉
 *
 * @author jj
 * @create 2018/8/15
 * @since 1.0.0
 */
public class Msg implements Serializable {
    private String title;

    private String content;

    private String etraInfo;

    public Msg(String title, String content, String etraInfo) {
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEtraInfo() {
        return etraInfo;
    }

    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }
}