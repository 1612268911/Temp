/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WebMVCConfig
 * Author:   jj
 * Date:     2018/8/14 20:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.config;

import com.cszt.domian.Msg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/error").setViewName("error");
    }
    @Bean
    public Msg getMsg(){
        Msg msg = new Msg("标题","内容","介绍");
        return msg;
    }
}