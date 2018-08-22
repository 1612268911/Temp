/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MergnMapper
 * Author:   jj
 * Date:     2018/7/30 20:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.NewTest.mapper;

import com.szzt.smart.NewTest.entity.Mergn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/30
 * @since 1.0.0
 */
@Mapper
@Repository
public interface MergnMapper extends BaseMapper<Mergn>{
    Mergn getMergn();
}
