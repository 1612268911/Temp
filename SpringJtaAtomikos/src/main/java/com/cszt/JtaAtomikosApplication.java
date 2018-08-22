/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MoreDatasourcesApplication
 * Author:   jj
 * Date:     2018/8/21 10:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import com.cszt.test1.config.DataConfigTest1;
import com.cszt.test2.config.DataConfigTest2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置多数据源〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties(value = {DataConfigTest2.class, DataConfigTest1.class})
public class JtaAtomikosApplication {
    public static void main(String[] args){
        SpringApplication.run(JtaAtomikosApplication.class,args);
    }
}