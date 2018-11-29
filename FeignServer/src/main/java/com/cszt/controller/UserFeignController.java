/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserFeignController
 * Author:   jj
 * Date:     2018/5/17 10:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import com.cszt.repository.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cszt.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/17
 * @since 1.0.0
 */
@Controller
public class UserFeignController {
    @Autowired
    private UserFeignClient userfeignclient;
    @RequestMapping(value="/getuser/{id}",method= RequestMethod.POST)
    @ResponseBody
    public User getUserclass(@PathVariable("id") int id){
        return userfeignclient.getUserclass(id);
    }
//    @RequestMapping( value="/test2",method = RequestMethod.POST)
//    @ResponseBody
//    public String test2() {
//        return userfeignclient.test2();
//    }
//    @RequestMapping( method = RequestMethod.POST,value="/test")
//    public String test(){
//        return userfeignclient.test();
//    }
//    @RequestMapping(value="/jj",method = RequestMethod.POST)
//    @ResponseBody
//    public String fw3(){
//        return "test&&&&&&&&";
//    }
}
