package com.cszt.netty;

/**
 * @author lilin
 * @create 2018/12/25 15:49
 * description: 弹幕对象
 */
public class Danmu {

    private String msg;

    private int x;

    private int y;

    public Danmu(String msg, int x, int y) {
        this.msg = msg;
        this.x = x;
        this.y = y;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}