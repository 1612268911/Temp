package com.cszt.controller;

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
 * Description: 测试UserController接口
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    /**
     * @Author lilin
     * @Description 测试UserController.createToken接口
     * @Date: 2018/8/30 9:55
     * @param:
     * @return:
     */
    @Test
    public void testCreateToken() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/createToken"))
                .andReturn();
        int status = result.getResponse().getStatus();
        //返回请求通过的拦截器
        //HandlerInterceptor[] interceptors = result.getInterceptors();
        String responseString = result.getResponse().getContentAsString();
        System.out.println("status->"+status+"  token->"+responseString);
//        Assert.assertEquals("请求错误", 100, status); //当 status!=100 时抛出java.lang.AssertionError: 请求错误
//        Assert.assertEquals("返回结果不一致", "this is index pageadmin", responseString);
    }
}