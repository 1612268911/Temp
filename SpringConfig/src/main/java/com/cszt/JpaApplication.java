/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JpaApplication
 * Author:   jj
 * Date:     2018/8/16 11:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import com.cszt.filter.MyFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.cszt")
@MapperScan("com.cszt.dao")
public class JpaApplication {
    public static void main(String[] args){
        SpringApplication.run(JpaApplication.class,args);
    }

    /**
     * 配置过滤器
     */
    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new MyFilter());
        filter.setName("myfilter");
        filter.addUrlPatterns("/*");
        filter.setOrder(1);
        return filter;
    }
}