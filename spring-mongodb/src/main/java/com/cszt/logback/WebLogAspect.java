package com.cszt.logback;

import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Web层日志切面  切面类
 *
 * @author houtt
 * @version 1.0.0
 * @date 2017-11-28
 */
@Aspect
@Order(1)
@Component
public class WebLogAspect {
  //logger取名为MONGODB
  private static final Logger logger = Logger.getLogger("MONGODB");

  @Pointcut("execution(public * com.cszt.controller.MongoController.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    // 获取HttpServletRequest
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 获取要记录的日志内容
    BasicDBObject logInfo = getBasicDBObject(request, joinPoint);
    //输出BasicDBObject对象的信息到mongodb
    System.out.println("logInfo-->"+logInfo);
    logger.info(logInfo);
  }

  //通过getBasicDBObject函数从HttpServletRequest和JoinPoint对象中获取请求信息，并组装成BasicDBObject
  private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
    // 基本信息
    BasicDBObject r = new BasicDBObject();
    r.append("requestURL", request.getRequestURL().toString());
    r.append("requestURI", request.getRequestURI());
    r.append("queryString", request.getQueryString());
    r.append("remoteAddr", request.getRemoteAddr());
    r.append("remoteHost", request.getRemoteHost());
    r.append("remotePort", request.getRemotePort());
    r.append("localAddr", request.getLocalAddr());
    r.append("localName", request.getLocalName());
    r.append("method", request.getMethod());
    r.append("headers", getHeadersInfo(request));
    r.append("parameters", request.getParameterMap());
    r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    r.append("args", Arrays.toString(joinPoint.getArgs()));
    return r;
  }

  /**
   * 获取头信息
   * 
   * @param request
   * @return
   */
  private Map<String, String> getHeadersInfo(HttpServletRequest request) {
    Map<String, String> map = new HashMap<String, String>();
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }
    return map;
  }

}
