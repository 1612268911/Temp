package com.cszt.controller;

import com.cszt.domain.User;
import com.cszt.repository.UserRepository;
import com.cszt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lilin
 * @create 2018/11/26 15:00
 * description:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        userService.delete(id);
    }
    @GetMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Integer id){
        return userService.findOne(id);
    }
    @PostMapping("/findAll")
    public Iterable<User> findAll(){
        return userService.findAll();
    }
}