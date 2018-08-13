package com.cszt; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: com.cszt.DownExcelApplication
 * Author:   jj
 * Date:     2018/8/8 14:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/8
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.cszt.mapper")
public class DownExcelApplication {
    public static void main(String[] args){
        SpringApplication.run(DownExcelApplication.class,args);
    }
}