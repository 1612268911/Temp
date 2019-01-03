package com.cszt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lilin
 * @create 2018/8/30 15:37
 * Description:
 */
@SpringBootApplication
@ComponentScan(value = "com.cszt")
public class ConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class,args);
    }
    @GetMapping("/getData")
    public Map<String,Object> getData(HttpServletResponse response){
        //设置跨域
        //log.info("/getData--->请求成功！");
        //response.setHeader("Access-Control-Allow-Origin", "*");
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code",200);
        result.put("message","success");
        return result;
    }
}