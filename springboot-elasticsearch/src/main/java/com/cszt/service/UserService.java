package com.cszt.service;

import com.cszt.domain.User;
import com.cszt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lilin
 * @create 2018/11/26 14:53
 * description:
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public User findOne(Integer id){
        Optional<User> byId = userRepository.findById(id);
        return byId.get()==null?null:byId.get();
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
}