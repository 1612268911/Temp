/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestService
 * Author:   jj
 * Date:     2018/6/13 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/6/13
 * @since 1.0.0
 */
@FeignClient(value="loginserver")
public interface TestService {
    @RequestMapping(value = "/user/test",method = RequestMethod.GET)
    public String test();
}