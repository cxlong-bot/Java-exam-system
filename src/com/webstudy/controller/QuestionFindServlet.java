package com.webstudy.controller;

import com.webstudy.dao.QuestionDao;
import com.webstudy.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: long
 * @date: 2021/3/29 11:39
 * @description:
 */
public class QuestionFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        List<Question> questionList = dao.findAll(request);
        request.setAttribute("questionList",questionList);
        request.getRequestDispatcher("/question_show.jsp").forward(request,response);
    }
}
