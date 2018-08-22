package com.szzt.smart.NewTest.service;

import com.szzt.smart.NewTest.entity.Score;
import com.szzt.smart.NewTest.mapper.ScoreMapper;
import com.szzt.smart.framework.mybatis.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.szzt.smart.framework.mybatis.mapper.FrameworkBaseMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
@Service
public class ScoreService extends BaseService<Score>
{

    @Autowired
    private ScoreMapper mapper;

    @Override
    protected FrameworkBaseMapper getMapper()
    {
        return mapper;
    }
}
