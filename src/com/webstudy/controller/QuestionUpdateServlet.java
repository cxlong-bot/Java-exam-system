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
 * @date: 2021/3/29 14:57
 * @description:
 */
public class QuestionUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId,title,optionA,optionB,optionC,optionD,answer;
        Question question = null;
        int result = 0;
        QuestionDao dao = new QuestionDao();
        questionId = request.getParameter("questionId");
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        question = new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
        result = dao.update(request,question);
        if(result != 0){
            request.setAttribute("info","试题更新成功");
        }else {
            request.setAttribute("info","试题更新失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
