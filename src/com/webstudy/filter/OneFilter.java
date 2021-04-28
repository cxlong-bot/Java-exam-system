package com.webstudy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: long
 * @date: 2021/3/28 11:49
 * @description: 过滤器实现类，保护资源文件，防止恶意访问
 */
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        request.setCharacterEncoding("utf-8");
        //调用请求对象读取请求包中请求行的uri，确定访问资源文件
        String uri = request.getRequestURI();
        //如果访问的是登录相关资源或者欢迎页面资源，放行
        if(uri.indexOf("login")!=-1 || uri.indexOf("Login")!=-1 || "/myWeb/".equals(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //获取用户的session，若用户不存在session返回null
        HttpSession session = request.getSession(false);
        //若用户不存在session，请求转发方式转至登录错误界面
        if(session == null){
            request.getRequestDispatcher("/User_LoginError.html").forward(servletRequest,servletResponse);
            return;
        }
        //若用户存在session，放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
