package com.ztdz; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: com.ztdz.ApplicationTest
 * Author:   jj
 * Date:     2018/7/18 20:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/18
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ztdz"})
public class ApplicationTest {
    public static void main(String[] args){
        SpringApplication.run(ApplicationTest.class,args);
    }
}