/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GetConfigFile
 * Author:   jj
 * Date:     2018/8/13 11:56
 * Description: 获取配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈获取配置文件〉
 *
 * @author jj
 * @create 2018/8/13
 * @since 1.0.0
 */
@Configuration
public class GetConfigFile {
    @Value("${mail.smtp.service}")
    private String smtpService;

    @Value("${mail.smtp.port}")
    private String smtpPort;

    @Value("${mail.from.smtp.pwd}")
    private String smtpPwd;

    @Value("${mail.from.address}")
    private String address;

    @Value("${mail.from.nicname}")
    private String nicName;

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

    public String getSmtpPwd() {
        return smtpPwd;
    }

    public void setSmtpPwd(String smtpPwd) {
        this.smtpPwd = smtpPwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }
}