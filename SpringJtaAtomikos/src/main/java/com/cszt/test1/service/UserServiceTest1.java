/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceTest1
 * Author:   jj
 * Date:     2018/8/21 10:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.test1.service;

import com.cszt.domain.User;
import com.cszt.test1.mapper.UserMapperTest1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Service
public class UserServiceTest1 {
    @Autowired
    private UserMapperTest1 userMapperTest1;

    public int getUser(User user){
        return userMapperTest1.insert(user);
    }
}