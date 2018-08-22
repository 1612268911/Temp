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
package com.cszt.test2.service;

import com.cszt.domain.User;
import com.cszt.test1.mapper.UserMapperTest1;
import com.cszt.test2.mapper.UserMapperTest2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Service
public class UserServiceTest2 {
    @Autowired
    private UserMapperTest2 userMapperTest2;
    @Autowired
    private UserMapperTest1 userMapperTest1;

    @Transactional//方法执行完才写入数据库，报错则回滚
    public int getUser(User user){
        user = new User();
        user.setId(2);
        user.setName("jj");
        user.setPwd("123456");
        int test1Count = userMapperTest1.insert(user);
        int test2Count = userMapperTest2.insert(user);
        //int i = 1/0;
        return test1Count+test2Count;
    }
}