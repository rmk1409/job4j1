<%@ page import="ru.job4j.http.logic.ValidateService" %>
<%@ page import="ru.job4j.http.model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 30.09.2019
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP</title>
</head>
<body>
<form action='<%=request.getContextPath()%>/create' method='post'>
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
    <%
        List<User> users = ValidateService.getInstance().findAll();
        for (User current : users) {
    %>
    <tr>
        <td><%=current.toString()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=current.getId()%>">
                <button>edit</button>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="id" value="<%=current.getId()%>">
                <button>delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
