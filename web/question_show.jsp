<%@ page import="com.webstudy.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: long
  Date: 2021/3/29
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Question> questionList = (List)request.getAttribute("questionList");
%>
<table border="2" align="center">
    <tr>
        <th>试题编号</th>
        <th>试题题目</th>
        <th>选项A</th>
        <th>选项B</th>
        <th>选项C</th>
        <th>选项D</th>
        <th>答案</th>
        <th colspan="2">操作</th>
    </tr>
    <%
        for(Question question:questionList){
    %>
    <tr>
        <td><%=question.getQuestionId()%></td>
        <td><%=question.getTitle()%></td>
        <td><%=question.getOptionA()%></td>
        <td><%=question.getOptionB()%></td>
        <td><%=question.getOptionC()%></td>
        <td><%=question.getOptionD()%></td>
        <td><%=question.getAnswer()%></td>
        <td><a href="/myWeb/question/delete?questionId=<%=question.getQuestionId()%>">删除试题</a></td>
        <td><a href="/myWeb/question/detail?questionId=<%=question.getQuestionId()%>">试题详情</a></td>
    </tr>
    <%
        }
    %>
</table>