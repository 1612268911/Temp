package com.cszt.repository;

import com.cszt.domain.TokenResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lilin
 * @create 2018/8/28 20:28
 * Description:
 */
@Repository
public interface TokenMapper {
    /**
     * @Author lilin
     * @Description 通过userId获取token值
     * @Date: 2018/8/28 20:35
     * @param:
     * @return:
     */
    @Select("select * from token where user_id = #{userId}")
    TokenResult getToken(Integer userId);
    /**
     * @Author lilin
     * @Description 插入token
     * @Date: 2018/8/29 14:59
     * @param:
     * @return:
     */
    @Insert("insert into token values(#{tokenId},#{token},#{invailDate},#{userId})")
    int saveToken(TokenResult token);
    /**
     * @Author lilin
     * @Description 更新token
     * @Date: 2018/8/29 14:59
     * @param:
     * @return:
     */
    @Update("update token set token = #{token},invail_date = #{invailDate} where token_id = #{tokenId}")
    int updateToken(TokenResult token);
}
