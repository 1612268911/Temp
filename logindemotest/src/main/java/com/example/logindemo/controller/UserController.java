/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   jj
 * Date:     2018/5/12 14:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.logindemo.controller;

import com.example.logindemo.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.logindemo.service.UserService;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@Controller
@RequestMapping("/demo/user")
@SessionAttributes(value={"user"},types={User.class})
public class UserController {
    @Autowired
    private UserService us;
    @Autowired
    private RestTemplate restTemplate;
    @ApiOperation(value = "用户登录" , notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详情实体类",required = true,dataType = "User")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(User user, Model model) {
        if(user.getUsername() !=null && !user.getUsername().equals("") && user.getPassword()!=null&& !user.getPassword().equals("")){
            User user2 = us.login(user);
            System.out.println("user2="+user2);
            if (user2 != null) {
                user2 = restTemplate.postForObject("http://localhost:8090/getuser/"+user2.getId(),Integer.TYPE,User.class);
                System.out.println("user2="+user2);
                model.addAttribute("user",user2);
                return "redirect:../success";
            } else {
                return "redirect:../login";
            }
        }else{
            return "redirect:../login";
        }
    }
    @Value("${server.port}")
    private String serverport;
    @ApiOperation(value = "获取用户信息" , notes = "根据user对象创建用户222")
    @ApiImplicitParam(name = "id",value = "用户详情实体类",required = true,dataType = "int",paramType = "path")
    @RequestMapping( value="/getuser/{id}",method = RequestMethod.POST)
    @ResponseBody
    public User getUserclass(@PathVariable("id")int id){
        return us.getUserclass(id);
    }
    @RequestMapping( value="/test",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "测试" , notes = "根据user对象创建用户333")
    public String test(){
        return "This is test!!! ------>serverport="+serverport;
    }
//    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
//    @ResponseBody
//    public User get(@PathVariable("id") int id){
//        return restTemplate.postForObject("http://localhost:8090/getuser/"+id,Integer.TYPE,User.class);
//    }
}
