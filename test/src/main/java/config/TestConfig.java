/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestConfig
 * Author:   jj
 * Date:     2018/6/14 10:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/6/14
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "cjj")//值的前缀 如server.port-->server
@PropertySource("classpath:application-test.yml")//配置文件路径
public class TestConfig {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}