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

import com.cszt.domain.Msg;
import com.cszt.domain.User;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public String getUser(String username, String password, HttpServletRequest request,Model model){
        User user = userService.getUser(username,password);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            Msg msg = msg();
            model.addAttribute("msg",msg);
            return "/home";
        }else{
            return "/login";
        }
    }
    @GetMapping("/home")
    public String home(Model model){
        Msg msg = msg();
        model.addAttribute("msg",msg);
        return "home";
    }
    @ResponseBody
    @GetMapping("/getUser")
    public User getUser(Integer id,HttpServletRequest request){
        User user = userService.findOne(id);
        request.setAttribute("user",user);
        return user;
    }
    @Bean
    public Msg msg(){
        Msg msg = new Msg("标题","内容","简介");
        return msg;
    }
}