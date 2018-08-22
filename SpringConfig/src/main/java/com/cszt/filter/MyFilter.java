/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyFilter
 * Author:   jj
 * Date:     2018/8/17 15:41
 * Description: 过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈过滤器〉
 *
 * @author jj
 * @create 2018/8/17
 * @since 1.0.0
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        System.out.println("filter-->uri=="+uri);
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}