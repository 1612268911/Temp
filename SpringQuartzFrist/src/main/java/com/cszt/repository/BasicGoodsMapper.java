package com.cszt.repository;

import com.cszt.domain.BasicGoods;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author lilin
 * @create 2018/9/6 14:39
 * description:
 */
@Repository
public interface BasicGoodsMapper {
    @Insert("insert into basic_goods(BGI_ID,BGI_NAME,BGI_PRICE,BGI_UNIT) values(#{id},#{name},#{price},#{unit})")
    public boolean addGoods(BasicGoods basicGoods);
}