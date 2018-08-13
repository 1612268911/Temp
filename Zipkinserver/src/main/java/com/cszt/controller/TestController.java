/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestController
 * Author:   jj
 * Date:     2018/5/23 16:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/23
 * @since 1.0.0
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/test")
    public String test(){
        return "zipkinservertest....";
    }
}
