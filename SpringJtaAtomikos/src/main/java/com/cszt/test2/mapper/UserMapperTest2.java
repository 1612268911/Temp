/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserMapperTest1
 * Author:   jj
 * Date:     2018/8/21 10:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.test2.mapper;

import com.cszt.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/21
 * @since 1.0.0
 */
@Repository
public interface UserMapperTest2 {
    int insert(User user);
}
