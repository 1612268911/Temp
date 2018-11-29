package com.cszt.repository;

import com.cszt.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author lilin
 * @create 2018/8/28 20:23
 * Description:
 */
@Repository
public interface UserMapper {
    /**
     * @Author lilin
     * @Description 用户名，密码查询用户信息
     * @Date: 2018/8/28 20:34
     * @param:
     * @return:
     */
    @Select("select u.id as userId,u.name as userName,u.pwd as passWord from user u where u.name = #{userName} and u.pwd = #{passWord}")
    User getUser(@Param("userName") String userName, @Param("passWord") String passWord);
}
