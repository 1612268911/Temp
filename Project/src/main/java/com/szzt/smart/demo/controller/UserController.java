package com.szzt.smart.demo.controller;

import com.szzt.smart.demo.entity.User;
import com.szzt.smart.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-06-08
 */
@RestController
@RequestMapping("/demo/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test/{id}")
    public  User get(@PathVariable Integer id)
    {
        return service.getUser(id);
    }
    @GetMapping("/test")
    public String test(String key,String value){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set(key,value);
        return "success";
    }
    @GetMapping("/Test")
    public String test(Integer id){
        String result = service.test(id);
        return result;
    }
    @GetMapping("/test2/{id}")
    public User test2(@PathVariable Integer id){
        User result = service.getUserTest(id);
        return result;
    }
}
