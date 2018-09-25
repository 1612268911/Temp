package com.cszt.service;

import com.cszt.MongoDBApplication;
import com.cszt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
        user.setAge(23);
        mongoTemplate.save(user);
    }
    public List<User> getProjects() {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is("老大");
        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }
}