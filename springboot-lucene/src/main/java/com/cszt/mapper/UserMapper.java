package com.cszt.mapper;

import com.cszt.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lilin
 * @create 2018/11/19 14:57
 * description:
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(id,name,pwd) values(NULL,#{name},#{pwd})")
    boolean insert(User user);
    @Insert("update user set name = #{name},pwd = #{pwd} where id = #{id}")
    boolean edit(User user);
    @Delete("delete from user where id = #{id}")
    boolean remove(int id);
    @Select("select t.id,t.name,t.pwd,t.createTime from user t")
    List<User> userList();
}