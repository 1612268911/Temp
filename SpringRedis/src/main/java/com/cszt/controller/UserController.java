/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   jj
 * Date:     2018/8/16 11:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUser")
    public User getUser(Integer id){
        return userService.getUser(id);
    }
//    //多环境配置文件 spring.profiles.active=dev
//    @Value("${requestName}")
//    private String requestName;
//
//    @GetMapping("/getRequestName")
//    public String getRequestName(){
//        return requestName;
//    }
}