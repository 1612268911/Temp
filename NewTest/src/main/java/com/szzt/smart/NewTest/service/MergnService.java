/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MergnService
 * Author:   jj
 * Date:     2018/7/30 20:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szzt.smart.NewTest.service;

import com.szzt.smart.NewTest.entity.Mergn;
import com.szzt.smart.NewTest.mapper.MergnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/7/30
 * @since 1.0.0
 */
@Service
public class MergnService {
    @Autowired
    MergnMapper mergnMapper;

    public Mergn getMergn(){
        return mergnMapper.getMergn();
    }
}