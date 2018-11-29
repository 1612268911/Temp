/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserJpa
 * Author:   jj
 * Date:     2018/8/14 16:22
 * Description: userJpa
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.repository;

import com.cszt.domian.User;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈userJpa〉
 *
 * @author jj
 * @create 2018/8/14
 * @since 1.0.0
 */
@Repository
public interface UserJpa{
    /**
     *
     * @param userName
     * @return
     */

    public User findUserByUserName(String userName);
}
