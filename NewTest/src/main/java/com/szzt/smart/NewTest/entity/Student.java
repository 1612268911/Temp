package com.szzt.smart.NewTest.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.szzt.smart.framework.mybatis.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
@Table(name="student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
   @ApiModelProperty(value = "编号")
   private String id;
    /**
     * 姓名
     */
   @ApiModelProperty(value = "姓名")
   private String name;


}
