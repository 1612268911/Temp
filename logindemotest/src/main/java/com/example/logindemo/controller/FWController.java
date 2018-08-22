/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FWController
 * Author:   jj
 * Date:     2018/5/13 10:14
 * Description: 转发页面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.logindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈转发页面〉
 *
 * @author jj
 * @create 2018/5/13
 * @since 1.0.0
 */
@Controller
public class FWController {

    @RequestMapping("/login")
    public String fw1(){
        return "login";
    }
    @RequestMapping(value="/success")
    public String fw2(){
        return "success";
    }
//    @RequestMapping( value="/test",method = RequestMethod.POST)
//    @ResponseBody
//    public String test(){
//        return this.restTemplate.getForObject("http://springcloud-provider-config/user/", String.class);
//    }
}
