package com.webstudy.controller;

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
 * @date: 2021/3/31 11:04
 * @description:
 */
public class ExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Question> questionList = (List)session.getAttribute("questionList");
        int score = 0;
        for(Question question:questionList){
            String answer = question.getAnswer();
            Integer questionId = question.getQuestionId();
            String userAnswer = request.getParameter("answer_"+questionId);
            if(answer.equals(userAnswer)){
                score += 25;
            }
        }
        request.setAttribute("info","恭喜你本次考试得到：" + score +"分");
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
