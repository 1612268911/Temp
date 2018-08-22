/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoggerMapper
 * Author:   jj
 * Date:     2018/8/17 14:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.dao;

import com.cszt.domain.LoggerEntity;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/17
 * @since 1.0.0
 */
@Repository
public interface LoggerMapper {
    /**
     * 保存log记录
     * @param loggerEntity
     */
    void save(LoggerEntity loggerEntity);
}