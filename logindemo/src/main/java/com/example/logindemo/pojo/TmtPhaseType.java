package com.example.logindemo.pojo;

import java.io.Serializable;

/**
 * <p>
 * 阶段分类表
 * </p>
 *
 String stepCategoryId 阶段分类ID;
 String parentStepCategoryId 父阶段分类ID;
 String name 名称;
 Integer sortNumber 序号;
 String isLeaf 是否叶子节点;
 *
 * @author 此代码为自动生成
 * @since 2018-05-24
 */
public class TmtPhaseType implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 阶段分类ID
     */
   private String stepCategoryId;
    /**
     * 父阶段分类ID
     */
   private String parentStepCategoryId;
    /**
     * 名称
     */
   private String name;
    /**
     * 序号
     */
   private Integer sortNumber;
    /**
     * 是否叶子节点
     */
   private String isLeaf;

    @Override
    public String toString() {
        return "TmtPhaseType{" +
                "stepCategoryId='" + stepCategoryId + '\'' +
                ", parentStepCategoryId='" + parentStepCategoryId + '\'' +
                ", name='" + name + '\'' +
                ", sortNumber=" + sortNumber +
                ", isLeaf='" + isLeaf + '\'' +
                '}';
    }
}
