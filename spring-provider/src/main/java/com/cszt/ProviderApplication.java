package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lilin
 * @create 2018/10/9 16:28
 * description: 模仿跨域访问请求端
 */
@SpringBootApplication
@Controller
@ComponentScan("com.cszt")
public class ProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(ProviderApplication.class,args);
    }
    @GetMapping("/index")
    public String getData(){
        System.out.println("))))))))))");
        return "/index";
    }
}