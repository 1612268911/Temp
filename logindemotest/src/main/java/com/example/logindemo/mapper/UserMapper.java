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
package com.example.logindemo.mapper;

import com.example.logindemo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
    @Select("select * from newuser where username = #{username} and username = #{password}")
    public User login(User user);

    public User getUserclass(int id);
}
