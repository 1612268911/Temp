package com.szzt.smart.NewTest.mapper;

import com.szzt.smart.NewTest.entity.Student;
import com.szzt.smart.framework.mybatis.mapper.FrameworkBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */

@Repository
@Mapper
public interface StudentMapper extends FrameworkBaseMapper<Student> {

}
