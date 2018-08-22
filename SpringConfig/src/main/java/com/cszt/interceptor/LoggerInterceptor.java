/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoggerInterceptor
 * Author:   jj
 * Date:     2018/8/17 11:04
 * Description: 记录请求日志拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.interceptor;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cszt.dao.LoggerMapper;
import com.cszt.domain.LoggerEntity;
import com.cszt.domain.User;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈记录请求日志拦截器〉
 *
 * @author jj
 * @create 2018/8/17
 * @since 1.0.0
 */
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoggerEntity logger = new LoggerEntity();
        //获取sessionId
        String sessionId = request.getRequestedSessionId();
        //获取uri
        String uri = request.getRequestURI();
        //请求参数json
        String paramData = JSONObject.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        //客户端ip
        //String clientIp = request.getRemoteAddr();
        String clientIp = getClientIp(request);
        //请求方法
        String aliMethod = request.getMethod();
        //设置请求类型 普通、ajax请求
        String aliType = null;
        //请求开始时间
        Date curDate = new Date();

        logger.setAliClientIp(clientIp);
        logger.setAliMethod(aliMethod);
        logger.setAliTime(curDate);
        logger.setParamData(paramData);
        logger.setUri(uri);
        logger.setSessionId(sessionId);
        logger.setAliType(aliType);

        request.setAttribute("logger",logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoggerEntity logger = (LoggerEntity)request.getAttribute("logger");
        //状态码
        int status = response.getStatus();
        //当前时间
        Date curDate = new Date();
        //返回值
        User user = (User)request.getAttribute("user");
        String returnData = JSONObject.toJSONString(user,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
        logger.setStatusCode(status+"");
        logger.setAliReturnTime(curDate);
        logger.setReturnDate(returnData);
        //生成aliId(必填)
        String aliId = UUID.randomUUID().toString();
        logger.setAliId(aliId);
        LoggerMapper loggerMapper = loggerMapper(request);
        loggerMapper.save(logger);
        System.out.println("logger=="+logger);
    }

    /**
     * 获取客户端ip
     * @param req
     * @return
     */
    public String getClientIp(HttpServletRequest req){
        String ip = req.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取loggerMapper（LoggerMapper不能注入时）
     * @param request
     * @return
     */
    @Bean
    public LoggerMapper loggerMapper(HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(LoggerMapper.class);
    }
}