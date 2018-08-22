package com.ztdz; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: com.ztdz.ControllerTest
 * Author:   jj
 * Date:     2018/7/18 20:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/18
 * @since 1.0.0
 */
@RestController
public class ControllerTest {
    /**
     * 测试post json
     * @param user
     * @return
     */
    @PostMapping("/test")
    public String test(@RequestBody User user){
        return "user = "+user;
    }

    /**
     * 测试 get text
     * @param value
     * @return
     */
    @GetMapping("/getTest")
    public String getTest(String value){
        return value + "get method...";
    }

    /**
     * 测试 post text
     * @param value
     * @return
     */
    @PostMapping("/testValue")
    public String StringParamTest(@RequestParam String value){
        return "value = "+value;
    }
}