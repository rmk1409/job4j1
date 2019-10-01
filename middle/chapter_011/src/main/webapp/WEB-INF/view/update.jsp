<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 01.10.2019
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/edit' method='post'>
    name: <input type='text' name='name' value='${user.name}'><br>
    login: <input type='text' name='login' value='${user.login}'><br>
    email: <input type='text' name='email' value='${user.email}'><br>
    <input type='hidden' name='id' value='${user.id}'>
    <input type='hidden' name='createdDate' value='${user.createDate.toString()}'>
    <input type='submit'>
</form>
</body>
</html>
