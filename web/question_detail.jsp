<%@ page import="com.webstudy.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: long
  Date: 2021/3/29
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Question question = (Question)request.getAttribute("question");
%>
<center>
    <form action="/myWeb/question/update" method="get">
        <table border="2">
            <tr>
                <td>试题编号</td>
                <td><input type="text" name="questionId" value="${requestScope.question.questionId}" readonly></td>
            </tr>
            <tr>
                <td>题目</td>
                <td><input type="text" name="title" value="${requestScope.question.title}"></td>
            </tr>
            <tr>
                <td>选项A</td>
                <td><input type="text" name="optionA" value="${requestScope.question.optionA}"></td>
            </tr>
            <tr>
                <td>选项B</td>
                <td><input type="text" name="optionB" value="${requestScope.question.optionB}"></td>
            </tr>
            <tr>
                <td>选项C</td>
                <td><input type="text" name="optionC" value="${requestScope.question.optionC}"></td>
            </tr>
            <tr>
                <td>选项D</td>
                <td><input type="text" name="optionD" value="${requestScope.question.optionD}"></td>
            </tr>
            <tr>
                <td>答案</td>
                <td>
                    <input type="radio" name="answer" value="A" ${"A" eq requestScope.question.answer?"checked":""}>A
                    <input type="radio" name="answer" value="B" ${"B" eq requestScope.question.answer?"checked":""}>B
                    <input type="radio" name="answer" value="C" ${"C" eq requestScope.question.answer?"checked":""}>C
                    <input type="radio" name="answer" value="D" ${"D" eq requestScope.question.answer?"checked":""}>D
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="更新试题"></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
</center>