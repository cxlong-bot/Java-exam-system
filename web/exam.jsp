<%@ page import="com.webstudy.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: long
  Date: 2021/3/31
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Question> questionList = (List)session.getAttribute("questionList");
%>
<form action="/myWeb/question/exam" method="get">
    <table border="2" align="center">
        <tr>
            <th>试题编号</th>
            <th>试题题目</th>
            <th>A</th>
            <th>B</th>
            <th>C</th>
            <th>D</th>
            <th>选择答案</th>
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
            <td>
                <input type="radio" name="answer_<%=question.getQuestionId()%>" value="A">A
                <input type="radio" name="answer_<%=question.getQuestionId()%>" value="B">B
                <input type="radio" name="answer_<%=question.getQuestionId()%>" value="C">C
                <input type="radio" name="answer_<%=question.getQuestionId()%>" value="D">D
            </td>
        </tr>
        <%
            }
        %>
        <tr align="center">
            <td colspan="3"><input type="submit" value="交卷"></td>
            <td colspan="4"><input type="reset" value="重做"></td>
        </tr>
    </table>
</form>
