/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MailEntity
 * Author:   jj
 * Date:     2018/8/13 11:26
 * Description: 邮件参数
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈邮件参数〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
public class MailEntity implements Serializable{
    /**
     * smtp服务器
     */
    private String smtpService;
    /**
     * smtp端口号
     */
    private String smtpPort;
    /**
     * 发送邮箱
     */
    private String fromMailAddress;
    /**
     * 发送邮箱的smtp口令
     */
    private String fromMailPassword;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 内容格式默认为html
     */
    private String contentType;
    /**
     * 接收邮件地址集合
     */
    private List<String> list = new ArrayList<String>();

    public String getSmtpService() {
        return smtpService;
    }

    public void setSmtpService(String smtpService) {
        this.smtpService = smtpService;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getFromMailAddress() {
        return fromMailAddress;
    }

    public void setFromMailAddress(String fromMailAddress) {
        this.fromMailAddress = fromMailAddress;
    }

    public String getFromMailPassword() {
        return fromMailPassword;
    }

    public void setFromMailPassword(String fromMailPassword) {
        this.fromMailPassword = fromMailPassword;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}