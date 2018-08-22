/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ConfigClientApplication
 * Author:   jj
 * Date:     2018/5/29 20:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/29
 * @since 1.0.0
 */
@EnableEurekaClient
@RefreshScope
@SpringBootApplication
@ComponentScan(basePackages = {"com"})
@RestController
public class ConfigClientApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigClientApplication.class,args);
    }
    @Value("${springname}")
    private String springname;
    @RequestMapping(value = "/springname")
    public String hi(){
        return springname;
    }
}
