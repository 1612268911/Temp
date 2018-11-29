package com.cszt.interceptor;

import com.cszt.domain.TokenResult;
import com.cszt.domain.User;
import com.cszt.repository.TokenMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lilin
 * @create 2018/8/29 9:16
 * Description: token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

    private static final long INVAIL_DATE = 7200000L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri-->"+uri);
        if(uri.equals("/token") || uri.equals("/error")){
            return true;
        }
        final String header = request.getHeader("X-YAuth-Token");

        if(StringUtils.isEmpty(header)){
            throw new Exception("header为空...");
        }
        String userId = request.getParameter("userId");
        TokenMapper tokenMapper = getTokenMapper(request);
        TokenResult tokenResult = tokenMapper.getToken(Integer.parseInt(userId));
        if(tokenResult==null){
            throw new Exception("没有token...");
        }
        if(tokenResult.getInvailDate()>INVAIL_DATE){
            throw new Exception("token失效...");
        }
        if(!tokenResult.getToken().equals(header)){
            throw new Exception("token错误...");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    @Bean
    public TokenMapper getTokenMapper(HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(TokenMapper.class);
    }
}