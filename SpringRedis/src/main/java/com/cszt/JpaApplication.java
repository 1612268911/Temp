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

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@EnableScheduling
public class JpaApplication {
    public static void main(String[] args){
        SpringApplication.run(JpaApplication.class,args);
    }
}