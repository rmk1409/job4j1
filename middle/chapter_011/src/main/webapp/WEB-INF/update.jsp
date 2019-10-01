<%@ page import="ru.job4j.http.logic.ValidateService" %>
<%@ page import="ru.job4j.http.model.User" %><%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 01.10.2019
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<%
    User user = ValidateService.getInstance().findById(Long.parseLong(request.getParameter("id")));
%>
<form action='<%=request.getContextPath()%>/edit' method='post'>
    name: <input type='text' name='name' value='<%=user.getName()%>'><br>
    login: <input type='text' name='login' value='<%=user.getLogin()%>'><br>
    email: <input type='text' name='email' value='<%=user.getEmail()%>'><br>
    <input type='hidden' name='id' value='<%=user.getId()%>'>
    <input type='hidden' name='createdDate' value='<%=user.getCreateDate().toString()%>'>
    <input type='submit'>
</form>
</body>
</html>
