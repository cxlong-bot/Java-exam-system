package com.webstudy.controller;

import com.webstudy.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: long
 * @date: 2021/3/29 14:00
 * @description:
 */
public class QuestionDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        QuestionDao dao = new QuestionDao();
        int result = 0;
        result = dao.delete(request,questionId);
        if(result != 0){
            request.setAttribute("info","试题删除成功");
        }else {
            request.setAttribute("info","试题删除失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
