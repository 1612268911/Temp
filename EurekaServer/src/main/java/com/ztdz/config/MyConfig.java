package com.ztdz.config;
/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Config
 * Author:   jj
 * Date:     2018/7/13 17:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/13
 * @since 1.0.0
 */
@Configuration
public class MyConfig {
    @Value("${send.message}")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}