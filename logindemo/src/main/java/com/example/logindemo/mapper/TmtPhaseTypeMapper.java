package com.example.logindemo.mapper;

import com.example.logindemo.pojo.TmtPhaseType;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 阶段分类表 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-05-24
 */

@Repository
public interface TmtPhaseTypeMapper {
    TmtPhaseType getTmtPhaseType(String id);
}
