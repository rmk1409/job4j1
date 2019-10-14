<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 03.10.2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${wrongCreds != null}">
    <div style="background-color: darkorange">
        <p>Wrong login/password</p>
        <p>Try again</p>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/login" method="post">
    <label> login: <input type="text" name="login"> </label><br>
    <label> passw: <input type="password" name="password"> </label><br>
    <input type="submit">
</form>
</body>
</html>
