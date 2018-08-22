/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ElectronicSignatureApplication
 * Author:   jj
 * Date:     2018/8/9 21:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/9
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cszt"})
public class ElectronicSignatureApplication {
    public static void main(String[] args){
        SpringApplication.run(ElectronicSignatureApplication.class,args);
    }
}