package com.cszt.service;

import com.cszt.domain.TokenResult;
import com.cszt.service.TokenService;
import com.cszt.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author lilin
 * @create 2018/8/30 9:28
 * Description: 测试测试TokenService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;
    /**
     * @Author lilin
     * @Description 测试TokenService saveToken方法
     * @Date: 2018/8/30 10:01
     * @param:
     * @return:
     */
    @Test
    public void saveToken(){
        TokenResult tokenResult = new TokenResult();
        tokenResult.setTokenId(UUID.randomUUID().toString());
        tokenResult.setToken("eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoieWFuZ3dlbnlhbyIsImFnZSI6IjQzIiwiYXVkIjoiY3kiLCJpc3MiOiJjeSIsImV4cCI6MTUzNTYwMTMzMn0.7xelA0dsolN-reed4DHMTijPJzZpK5uLgZrBLSRJfWNooRQgDOghBSFGxSEvhubf4DyPAXbvRUJTey_S6zbezA");
        long invailDate = System.currentTimeMillis()+7200*1000;
        tokenResult.setInvailDate(invailDate);
        tokenResult.setUserId(3);
        int count = tokenService.updateToken(tokenResult);
        Assert.assertEquals("插入失败！",1,count);
    }
}