<%@ page import="com.webstudy.entity.Users" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: long
  Date: 2021/3/28
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Users> usersList = (List)request.getAttribute("usersList");
%>
<table border="2" align="center">
    <tr>
        <th>用户编号</th>
        <th>用户名</th>
        <th>用户密码</th>
        <th>用户性别</th>
        <th>用户邮箱</th>
        <th>操作用户</th>
    </tr>
    <%
        for(Users user:usersList){
    %>
        <tr>
            <td><%=user.getUserId()%></td>
            <td><%=user.getUserName()%></td>
            <td><%=user.getUserPassword()%></td>
            <td><%=user.getUserSex()%></td>
            <td><%=user.getUserEmail()%></td>
            <td><a href="/myWeb/user/delete?userId=<%=user.getUserId()%>">删除用户</a></td>
        </tr>
    <%
        }
    %>
</table>
