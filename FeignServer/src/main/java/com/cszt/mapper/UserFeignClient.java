/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FeignClient
 * Author:   jj
 * Date:     2018/5/14 12:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.mapper;

import fallback.UserFallBack;
import fallback.UserFallFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cszt.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/5/14
 * @since 1.0.0
 */
@FeignClient(value="loginserver",fallbackFactory = UserFallFactory.class/*fallback = UserFallBack.class*/)
public interface UserFeignClient {
    @RequestMapping(value="/user/getuser/{id}",method= RequestMethod.POST)
    public User getUserclass(@PathVariable("id")int id);

//    @RequestMapping( method = RequestMethod.POST,value="/user/test2")
//    public String test2();
//    @RequestMapping( method = RequestMethod.POST,value="/test")
//    public String test();
}

