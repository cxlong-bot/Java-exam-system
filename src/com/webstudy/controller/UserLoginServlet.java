package com.webstudy.controller;

import com.webstudy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: long
 * @date: 2021/3/25 15:45
 * @description:用户登录Servlet
 */
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,userPassword;
        UserDao dao = new UserDao();
        int result = 0;
        //用户登录验证
        request.setCharacterEncoding("utf-8");
        userName = request.getParameter("userName");
        userPassword = request.getParameter("userPwd");
        result = dao.login(userName,userPassword);
        if(result != 0){
            //登录成功，若用户不存在session给用户发放一个session令牌，防止恶意访问
            request.getSession();
            response.sendRedirect("/myWeb/index.html");
        }else {
            //登录失败，响应登录错误界面
            response.sendRedirect("/myWeb/User_LoginError.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
