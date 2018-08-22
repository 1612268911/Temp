/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   jj
 * Date:     2018/8/21 10:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.test1.service.UserServiceTest1;
import com.cszt.test2.service.UserServiceTest2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceTest1 userServiceTest1;
    @Autowired
    private UserServiceTest2 userServiceTest2;

    @GetMapping("/getUserTest1")
    public User getUserTest1(Integer id){
        return userServiceTest1.getUser(id);
    }
    @GetMapping("/getUserTest2")
    public User getUserTest2(Integer id){
        return userServiceTest2.getUser(id);
    }
    @RequestMapping(value = "/insert1",method = RequestMethod.POST)
    public int insert1(@RequestBody User user){
        return userServiceTest1.insert(user);
    }
    @RequestMapping(value = "/insert2",method = RequestMethod.POST)
    public int insert2(@RequestBody User user){
        return userServiceTest2.insert(user);
    }
    @RequestMapping(value = "/insertMerge",method = RequestMethod.POST)
    public int insertMerge(@RequestBody User user){
        return userServiceTest1.insertMerge(user);
    }
}