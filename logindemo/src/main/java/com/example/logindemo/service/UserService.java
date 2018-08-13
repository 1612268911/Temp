/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   jj
 * Date:     2018/5/12 14:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.logindemo.service;

import com.example.logindemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.logindemo.pojo.User;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper um;
    public User login(User user){
        return um.login(user);
    }
    public User getUserclass(int id){
        return um.getUserclass(id);
    }
    public List<User> select(){
        return um.select();
    }
}
