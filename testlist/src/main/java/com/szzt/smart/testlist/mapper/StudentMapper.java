/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StudentMapper
 * Author:   jj
 * Date:     2018/8/10 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.testlist.mapper;

import com.bzwframework.persistencelayer.mybatis.BaseMapper;
import com.szzt.smart.testlist.entity.Student;
import com.szzt.smart.testlist.entity.condition.StudentCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/10
 * @since 1.0.0
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 获取所有学生的分数
     * @return
     */
    List<Student> getScoreList(StudentCondition studentCondition);
}
