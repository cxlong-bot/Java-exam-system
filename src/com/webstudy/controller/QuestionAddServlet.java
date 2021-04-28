package com.webstudy.controller;

import com.webstudy.dao.QuestionDao;
import com.webstudy.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author: long
 * @date: 2021/3/29 11:09
 * @description:
 */
public class QuestionAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        QuestionDao dao = new QuestionDao();
        Question question = null;
        int result = 0;
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        Date start = new Date();
        result = dao.add(question,request);
        Date end = new Date();
        System.out.println("试题添加花费" + (start.getTime()-end.getTime()) + "毫秒");
        if(result != 0){
            request.setAttribute("info","试题添加成功");
        }else {
            request.setAttribute("info","试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
