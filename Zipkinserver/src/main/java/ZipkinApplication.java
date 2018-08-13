/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ZipkinApplication
 * Author:   jj
 * Date:     2018/5/22 20:21
 * Description: zipkin启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import zipkin.server.EnableZipkinServer;

/**
 * 〈一句话功能简述〉<br>
 * 〈zipkin启动类〉
 *
 * @author jj
 * @create 2018/5/22
 * @since 1.0.0
 */
@SpringBootApplication
//@EnableZipkinServer
@EnableZipkinStreamServer
@EnableEurekaClient
@ComponentScan(basePackages = {"controller"})
public class ZipkinApplication {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(ZipkinApplication.class,args);
    }
}
