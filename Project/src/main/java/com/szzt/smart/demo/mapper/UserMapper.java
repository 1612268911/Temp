package com.szzt.smart.demo.mapper;

import com.szzt.smart.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-06-08
 */

@Repository
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User getUser(Integer id);
}
