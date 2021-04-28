package com.webstudy.controller;

import com.webstudy.dao.QuestionDao;
import com.webstudy.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author: long
 * @date: 2021/3/31 10:15
 * @description:
 */
public class QuestionRandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        HttpSession session = request.getSession(false);
        List<Question> questionList = dao.findRand(request);
        session.setAttribute("questionList",questionList);
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
