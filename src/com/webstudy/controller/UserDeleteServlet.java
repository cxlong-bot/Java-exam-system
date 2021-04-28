package com.webstudy.controller;

import com.webstudy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: long
 * @date: 2021/3/25 14:56
 * @description:用户删除Servlet
 */
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        userId = request.getParameter("userId");
        UserDao dao = new UserDao();
        int result = dao.delete(userId);
        if(result != 0){
            request.setAttribute("info","用户信息删除成功");
        }else {
            request.setAttribute("info","用户信息删除失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
