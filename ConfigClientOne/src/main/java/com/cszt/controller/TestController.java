/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestController
 * Author:   jj
 * Date:     2018/6/13 9:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/6/13
 * @since 1.0.0
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${springname}")
    private String springname;
    @RequestMapping(value = "/springname")
    public String hi(){
        return springname;
    }
}