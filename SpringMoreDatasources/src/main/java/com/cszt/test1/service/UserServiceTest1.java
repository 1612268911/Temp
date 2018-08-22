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
public class UserServiceTest1 {
    @Autowired
    private UserMapperTest1 userMapperTest1;
    @Autowired
    private UserMapperTest2 userMapperTest2;

    public User getUser(Integer id){
        return userMapperTest1.getUser(id);
    }
    @Transactional(value = "test1TransactionManager")
    public int insert(User user){
        return userMapperTest1.insert(user);
    }
    @Transactional(value = "test1TransactionManager")
    public int insertMerge(User user){
        int count1 = userMapperTest1.insert(user);
        int count2 = userMapperTest2.insert(user);
        int a = 1/0;
        return count1+count2;
    }
}