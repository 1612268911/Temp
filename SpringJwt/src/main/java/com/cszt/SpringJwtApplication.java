package com.cszt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lilin
 * @create 2018/8/28 17:05
 * Description: SpringJwt启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cszt.mapper"})
public class SpringJwtApplication {
    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(SpringJwtApplication.class);
        //不显示banner
        //springApplication.setBannerMode(Banner.Mode.OFF);

        springApplication.run(args);
        //原启动
        //SpringApplication.run(SpringJwtApplication.class,args);
    }
}