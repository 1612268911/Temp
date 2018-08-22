/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserJpa
 * Author:   jj
 * Date:     2018/8/16 11:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.dao;

import com.cszt.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/16
 * @since 1.0.0
 */
@Repository
public interface UserJpa {
    @Select("select u.u_id as id,u.u_username as username,u.u_password as password from newUser u where u.u_id = #{id}")
    User findOne(Integer id);
}
