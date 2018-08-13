/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StudentService
 * Author:   jj
 * Date:     2018/8/10 15:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.testlist.service;

import com.szzt.smart.testlist.entity.Student;
import com.szzt.smart.testlist.entity.condition.StudentCondition;
import com.szzt.smart.testlist.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/10
 * @since 1.0.0
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getScoreList(StudentCondition studentCondition){
        return studentMapper.getScoreList(studentCondition);
    }
}