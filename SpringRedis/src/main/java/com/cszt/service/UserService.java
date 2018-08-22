/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   jj
 * Date:     2018/8/16 11:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.service;

import com.cszt.dao.UserJpa;
import com.cszt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@Service
public class UserService {
    @Autowired
    private UserJpa userJpa;

    public User getUser(Integer id){
        return userJpa.findOne(id);
    }
}