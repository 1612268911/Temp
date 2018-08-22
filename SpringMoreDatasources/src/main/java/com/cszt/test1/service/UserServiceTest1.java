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
	/**
	**多个数据源未统一管理事务时必须指定事务管理器（test1TransactionManager）
	**方法无异常时数据入库，有异常时回滚(不管是否执行：userMapperTest1.insert(user);)
	*/
    @Transactional(value = "test1TransactionManager")
    public int insert(User user){
        return userMapperTest1.insert(user);
    }
	//仅对test1事务管理，当出现异常（int a = 1/0;）test1未入库，test2入库
    @Transactional(value = "test1TransactionManager")
    public int insertMerge(User user){
        int count1 = userMapperTest1.insert(user);
        int count2 = userMapperTest2.insert(user);
        //int a = 1/0;
        return count1+count2;
    }
}