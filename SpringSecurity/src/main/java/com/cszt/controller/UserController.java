/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   jj
 * Date:     2018/8/14 17:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */

import com.cszt.config.WebMVCConfig;
import com.cszt.domian.Msg;
import com.cszt.domian.Roles;
import com.cszt.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(types = User.class)
public class UserController {

    @Autowired
    private WebMVCConfig webMVCConfig;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/admin/users")
    public String admin(){
        return "This is admin/users";
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/user/users")
    public String user(){
        return "This is user/users";
    }

    @RequestMapping(value = "/home",method=RequestMethod.GET)
    public String index(Model model){
        Msg msg = webMVCConfig.getMsg();
        model.addAttribute("msg",msg);
        return "home";
    }
}
