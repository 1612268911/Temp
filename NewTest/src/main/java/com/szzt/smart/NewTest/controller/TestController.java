/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestController
 * Author:   jj
 * Date:     2018/7/30 20:32
 * Description: 两个表中有相同字段sql测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.NewTest.controller;

import com.szzt.smart.NewTest.entity.Mergn;
import com.szzt.smart.NewTest.service.MergnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈两个表中有相同字段sql测试〉
 *
 * @author jj
 * @create 2018/7/30
 * @since 1.0.0
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    MergnService mergnService;

    @GetMapping("/getMergn")
    public Mergn getMergn(){
        return mergnService.getMergn();
    }
}