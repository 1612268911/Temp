package com.szzt.smart.NewTest.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.szzt.smart.framework.mybatis.entity.BaseEntity;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel
@Table(name="user")
public class User extends BaseEntity<Integer> {

    private static final long serialVersionUID = 1L;
   @ApiModelProperty(value = "")
   private String name;
   @ApiModelProperty(value = "")
   private String pwd;


}
