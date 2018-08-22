/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoggerEntity
 * Author:   jj
 * Date:     2018/8/17 11:00
 * Description: 请求日志实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.domain;

import org.springframework.context.annotation.Primary;

import javax.annotation.Generated;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈记录请求日志实体〉
 *
 * @author jj
 * @create 2018/8/17
 * @since 1.0.0
 */
public class LoggerEntity {
    /**
     编号
     */
    private String aliId;
    /**
     客户端请求ip
     */
    private String aliClientIp;
    /**
     客户端请求路径
     */
    private String uri;
    /**
     终端请求方式 普通、ajax
     */
    private String aliType;
    /**
     终端请求方式post,get等
     */
    private String aliMethod;
    /**
     请求参数内容json
     */
    private String paramData;
    /**
     session标识
     */
    private String sessionId;
    /**
     请求时间
     */
    private Date aliTime;
    /**
     接口返回时间
     */
    private Date aliReturnTime;
    /**
     接口返回数据json
     */
    private String returnDate;

    /**
     * 状态码
     */
    private String statusCode;

    public String getAliId() {
        return aliId;
    }

    public void setAliId(String aliId) {
        this.aliId = aliId;
    }

    public String getAliClientIp() {
        return aliClientIp;
    }

    public void setAliClientIp(String aliClientIp) {
        this.aliClientIp = aliClientIp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAliType() {
        return aliType;
    }

    public void setAliType(String aliType) {
        this.aliType = aliType;
    }

    public String getAliMethod() {
        return aliMethod;
    }

    public void setAliMethod(String aliMethod) {
        this.aliMethod = aliMethod;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getAliTime() {
        return aliTime;
    }

    public void setAliTime(Date aliTime) {
        this.aliTime = aliTime;
    }

    public Date getAliReturnTime() {
        return aliReturnTime;
    }

    public void setAliReturnTime(Date aliReturnTime) {
        this.aliReturnTime = aliReturnTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "LoggerEntity{" +
                "aliId=" + aliId +
                ", aliClientIp='" + aliClientIp + '\'' +
                ", uri='" + uri + '\'' +
                ", aliType='" + aliType + '\'' +
                ", aliMethod='" + aliMethod + '\'' +
                ", paramData='" + paramData + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", aliTime=" + aliTime +
                ", aliReturnTime=" + aliReturnTime +
                ", returnDate='" + returnDate + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}