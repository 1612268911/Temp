package com.cszt.controller;

import com.cszt.service.DelaySendSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilin
 * @create 2018/9/7 14:33
 * description: 消息延迟消费测试
 */
@RestController
public class DelaySendController {
    @Autowired
    private DelaySendSerivce delaySendSerivce;

    @GetMapping("/delaySendTest")
    public String delaySendTest() throws Exception {
        delaySendSerivce.sendMessage();
        return "success";
    }
}