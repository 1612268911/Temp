/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ConfigServerApplication
 * Author:   jj
 * Date:     2018/5/28 20:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/28
 * @since 1.0.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableConfigServer
@ComponentScan(basePackages = {"com"})
public class ConfigServerApplication extends WebMvcConfigurerAdapter{
    public static void main(String[] args){
        SpringApplication.run(ConfigServerApplication.class,args);
    }
    @Override
    //这个方法是专门用来配置内容裁决的一些参数的
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* 是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(false);
    }
}
