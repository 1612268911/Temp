/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MailContentType
 * Author:   jj
 * Date:     2018/8/13 14:06
 * Description: 邮件类型枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.util;

/**
 * 〈一句话功能简述〉<br>
 * 〈邮件类型枚举〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
public enum MailContentType {
    TEXT("text"),
    HTML("text/templates;charset=utf-8");

    private String value;

    MailContentType(String contentType){
        this.value = contentType;
    }
    public String getContentType(){
        return value;
    }
}
