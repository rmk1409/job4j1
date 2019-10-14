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
    <label>name:
        <input type='text' name='name' value='${dto.name}'>
    </label><br>
    <label>login:
        <input type='text' name='login' value='${dto.login}'>
    </label><br>
    <label>password:
        <input type='text' name='password' value='${dto.password}'>
    </label><br>
    <label>email:
        <input type='text' name='email' value='${dto.email}'>
    </label><br>
    <label>role:
        <c:choose>
            <c:when test="${'admin'.equals(user.role)}">
                <select name="role">
                    <option value="everyone" ${'everyone'.equals(dto.role)? 'selected': ''}>everyone</option>
                    <option value="admin" ${'admin'.equals(dto.role)? 'selected': ''}>admin</option>
                </select>
            </c:when>
            <c:otherwise>
                ${dto.role}
            </c:otherwise>
        </c:choose>
    </label><br>
    <input type='hidden' name='id' value='${dto.id}'>
    <input type='hidden' name='createdDate' value='${dto.createDate}'>
    <input type='submit'>
</form>
</body>
</html>
