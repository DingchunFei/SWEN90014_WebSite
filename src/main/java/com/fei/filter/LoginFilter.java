package com.fei.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebFilter(filterName = "LoginFilter", urlPatterns = "/stimuli/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----LoginFilter过滤器初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----LoginFilter过滤器运行----");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if(session.getAttribute("userInfo")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            resp.sendRedirect("/login/signin");
        }

    }

    @Override
    public void destroy() {
        System.out.println("----LoginFilter过滤器销毁----");
    }
}
