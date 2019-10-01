<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 30.09.2019
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSP</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/create' method='post'>
    <p>Create new user</p>
    name: <input type='text' name='name'><br>
    login: <input type='text' name='login'><br>
    email: <input type='text' name='email'><br>
    <input type='submit'>
</form>
<table>
    <tr>
        <th>User</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="current">
        <tr>
            <td><c:out value="${current}"/>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="${current.id}">
                    <button>edit</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="id" value="${current.id}">
                    <button>delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
