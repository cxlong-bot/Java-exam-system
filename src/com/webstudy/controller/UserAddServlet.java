package com.webstudy.controller;

import com.webstudy.dao.UserDao;
import com.webstudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author: long
 * @date: 2021/3/24 14:23
 * @description:用户注册Servlet
 */
public class UserAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,userPwd,userSex,userEmail;
        UserDao dao = new UserDao();
        Users user = null;
        int result = 0;
        //将请求参数填充到一个user对象中
        userName = request.getParameter("userName");
        userPwd = request.getParameter("userPwd");
        userSex = request.getParameter("userSex");
        userEmail = request.getParameter("userEmail");
        user = new Users(null,userName,userPwd,userSex,userEmail);
        //调用dao对象的添加方法将user对象添加到数据库
        Date start = new Date();
        result = dao.add(user,request);
        Date end = new Date();
        System.out.println("注册用户花费" + (start.getTime()-end.getTime()) + "毫秒");
        //打印响应信息
        if(result != 0){
            request.setAttribute("info","用户信息注册成功");
        }else {
            request.setAttribute("info","用户信息注册失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
