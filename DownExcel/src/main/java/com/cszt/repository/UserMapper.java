/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserMapper
 * Author:   jj
 * Date:     2018/5/12 13:50
 * Description: 连接数据库
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.repository;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.cszt.pojo.User;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈连接数据库〉
 *
 * @author jj
 * @create 2018/5/12
 * @since 1.0.0
 */
@Repository
public interface UserMapper {
    @Select("select * from user")
    public List<User> select();
    //批量插入数据库
    public boolean insert(List<User> userList);
}
