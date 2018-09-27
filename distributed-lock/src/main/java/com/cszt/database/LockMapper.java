package com.cszt.database;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author lilin
 * @create 2018/9/27 20:19
 * description:
 */
@Repository
public interface LockMapper {
    /**
     * @author lilin
     * @description 添加锁
     * @date: 2018/9/27 20:24
     * @param:
     * @return:
     */
    @Insert("insert into tpt_lock(lock_name,value) values(#{lockName},'LOCKED')")
    public void save(String lockName);
    /**
     * @author lilin
     * @description 删除锁
     * @date: 2018/9/27 20:25
     * @param:
     * @return:
     */
    @Delete("delete from tpt_lock where lock_name = #{lockName}")
    public void del(String lockName);
    /**
     * @author lilin
     * @description 验证锁是否存在
     * @date: 2018/9/27 20:25
     * @param:
     * @return:
     */
    @Select("select value from tpt_lock where lock_name = #{lockName}")
    public String check(String lockName);
}