package com.cszt.service;

import com.cszt.MongoDBApplication;
import com.cszt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lilin
 * @create 2018/9/25 11:06
 * description:
 */
@Service
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(){
        User user = new User();
        user.setName("老大");
        user.setAge(21);
        mongoTemplate.save(user);
    }
    /**
     * @author lilin
     * @description 获取所有user
     * @date: 2018/9/26 16:23
     * @param:
     * @return:
     */
    public List<User> getAllUser() {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "age")));
        return mongoTemplate.findAll(User.class);
    }
    public List<User> getUser(String name){
        Criteria criteria = Criteria.where("name").is(name);//语句
        Query query = new Query();
        query.addCriteria(criteria);
        query.limit(1);
        return mongoTemplate.find(query,User.class);
    }
}