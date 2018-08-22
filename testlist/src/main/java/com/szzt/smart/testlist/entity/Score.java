package com.szzt.smart.testlist.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
@Data
@ToString
public class Score implements Serializable{

   private static final long serialVersionUID = 1L;
   /**
    * 分数id
    */
   private String scoreId;
   /**
    * 学生编号
    */
   private String sid;
   /**
    * 分数
    */
   private Double score;

}
