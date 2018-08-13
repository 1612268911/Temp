package com.szzt.smart.testlist.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-07-16
 */
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

   public String getScoreId() {
      return scoreId;
   }

   public void setScoreId(String scoreId) {
      this.scoreId = scoreId;
   }

   public String getSid() {
      return sid;
   }

   public void setSid(String sid) {
      this.sid = sid;
   }

   public Double getScore() {
      return score;
   }

   public void setScore(Double score) {
      this.score = score;
   }
}
