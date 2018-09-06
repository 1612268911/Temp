package com.cszt.domain;

import java.io.Serializable;

/**
 * @author lilin
 * @create 2018/9/6 14:35
 * description: 商品信息
 */
public class BasicGoods implements Serializable {
    /**
     * id
     */
    private String id;

    private String name;

    private double price;
    /**
     * 单位
     */
    private String unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}