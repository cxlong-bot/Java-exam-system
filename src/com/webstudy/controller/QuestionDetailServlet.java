package com.webstudy.controller;

import com.webstudy.dao.QuestionDao;
import com.webstudy.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: long
 * @date: 2021/3/29 14:20
 * @description:
 */
public class QuestionDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        Question question = null;
        QuestionDao dao = new QuestionDao();
        question = dao.find(request,questionId);
        request.setAttribute("question",question);
        request.getRequestDispatcher("/question_detail.jsp").forward(request,response);
    }
}
