package com.szzt.smart.NewTest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.szzt.smart.framework.mybatis.entity.BaseEntity;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
@Table(name="score")
public class Score implements Serializable{

    private static final long serialVersionUID = 1L;
   @Id
   private Integer scoreid;
   @ApiModelProperty(value = "")
   private Double score;
   @ApiModelProperty(value = "")
   private String project;

}
