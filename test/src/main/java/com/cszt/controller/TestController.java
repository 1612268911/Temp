/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestController
 * Author:   jj
 * Date:     2018/5/16 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/16
 * @since 1.0.0
 */
@Controller
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
//    @GetMapping("/test")
//    @ResponseBody
//    public String test(){
//        return restTemplate.getForObject("http://loginserver/user/test2",String.class);
//    }

    @Autowired
    TestConfig testConfig;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "value="+testConfig.getName();
    }
}
