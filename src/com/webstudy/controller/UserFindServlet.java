package com.webstudy.controller;

import com.webstudy.dao.UserDao;
import com.webstudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: long
 * @date: 2021/3/25 11:23
 * @description:用户查询Servlet
 */
public class UserFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        //利用UserDao实例的查询命令查询数据库所有用户信息，并推送到List
        List<Users> usersList = dao.findAll();
        request.setAttribute("usersList",usersList);
        request.getRequestDispatcher("/user_show.jsp").forward(request,response);
    }
}
