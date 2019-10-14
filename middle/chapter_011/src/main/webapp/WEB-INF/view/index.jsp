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
<h3>Hi, ${user.login}</h3>
<c:if test="${'admin'.equals(user.role)}">
    <form action='${pageContext.servletContext.contextPath}/create' method='post'>
        <h4>Create new user</h4>
        <label>name:
            <input type='text' name='name'>
        </label><br>
        <label>login:
            <input type='text' name='login'>
        </label><br>
        <label>password:
            <input type='password' name='password'>
        </label><br>
        <label>email:
            <input type='text' name='email'>
        </label><br>
        <label>role:
            <select name="role">
                <option value="everyone" selected>everyone</option>
                <option value="admin">admin</option>
            </select>
        </label> <br>
        <input type='submit' value="Add">
    </form>
</c:if>
<button onclick="window.location.href = '${pageContext.servletContext.contextPath}/logout';">Logout</button>
<br>
<table border="1">
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
                    <button ${'admin'.equals(user.role) || current.id == user.id ? '' : 'disabled'}>edit</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="id" value="${current.id}">
                    <button ${'admin'.equals(user.role)? '' : 'disabled'}>delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
