/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: WebSecurityConfig
 * Author:   jj
 * Date:     2018/8/14 16:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.cszt.service.SecurityProvider;
import com.cszt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private SecurityProvider securityProvider;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(securityProvider);//user Details Service验证
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);   //userDetailsService验证
    }
    /**
      * 重写该方法，添加自定义用户
     * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN","USER")
//                .and()
//                .withUser("terry").password("terry").roles("USER")
//                .and()
//                .withUser("larry").password("larry").roles("USER");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
//                .anyRequest()               // 任何请求,登录后可以访问
//                .authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")//需要的权限，不是角色 用hasAuthority
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/home").authenticated()//登录后可访问
                .antMatchers("/").permitAll()
                .and()
                .formLogin().loginPage("/login")//自定义登录页面 action=/login method=post
                .defaultSuccessUrl("/home")//
//                .failureUrl("/error")
                .permitAll() //登录页面可任意访问
                .and()
                .logout().permitAll();//注销请求可任意访问
    }
}