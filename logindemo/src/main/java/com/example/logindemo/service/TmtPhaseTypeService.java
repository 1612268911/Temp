package com.example.logindemo.service;

import com.example.logindemo.mapper.TmtPhaseTypeMapper;
import com.example.logindemo.pojo.TmtPhaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 阶段分类表 服务实现类
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-05-24
 */
@Service
public class TmtPhaseTypeService
{

    @Autowired
    private TmtPhaseTypeMapper mapper;

    public TmtPhaseType getTmtPhaseType(String id){
        TmtPhaseType type = mapper.getTmtPhaseType(id);
        return type;
    }
}
