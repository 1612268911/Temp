/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SendSystemMessage
 * Author:   jj
 * Date:     2018/8/10 15:59
 * Description: 获取发送系统消息的配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.testlist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈获取发送系统消息的配置〉
 *
 * @author jj
 * @create 2018/8/10
 * @since 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.sendSystemMessage")
@PropertySource("classpath:config/application-test.yml")
public class SendSystemMessage {
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}