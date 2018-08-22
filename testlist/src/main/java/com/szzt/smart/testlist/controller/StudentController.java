/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StudentController
 * Author:   jj
 * Date:     2018/8/10 15:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.testlist.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.szzt.smart.testlist.config.SendShortMessage;
import com.szzt.smart.testlist.config.SendSystemMessage;
import com.szzt.smart.testlist.entity.Student;
import com.szzt.smart.testlist.entity.condition.StudentCondition;
import com.szzt.smart.testlist.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/10
 * @since 1.0.0
 */
@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/getScoreList")
    public Map<String,Object> getScoreList(@RequestBody StudentCondition studentCondition){
        Map<String,Object> map = new HashMap<>();
        if(studentCondition.getPage()==0){
            studentCondition.setPage(1);
        }
        if(studentCondition.getPageSize()==0){
            studentCondition.setPageSize(2);
        }
        List<Student> studentList = studentService.getScoreList(studentCondition);
        long total = studentCondition.getTotalRecord();
        //@slf4j
        log.info("获取到的数据：{}",  studentList);
        map.put("data",studentList);
        map.put("total",total);
        return map;
    }
//    @Value("${sendSystemMessage.enable}")
////    private String isSendSystemMessage;

//    @Value("${sendShortMessage.enable}")
//    private String isSendShortMessage;
    @Autowired
    private SendSystemMessage sendSystemMessage;
    @Autowired
    private SendShortMessage sendShortMessage;

    @GetMapping("/getConfigFile")
    public String getConfigFile(){
        String message = "是否发送短信："+sendShortMessage.getEnable()+"   "+"是否发送系统消息："+sendSystemMessage.getEnable();
        return message;

    }
}