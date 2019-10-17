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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function checkCreds() {
            $.post("${pageContext.servletContext.contextPath}/login"
                , JSON.stringify({login: $("#login").val(), password: $("#password").val()})
                , function (dataFromServer) {
                    if (dataFromServer === "false") {
                        $("#alert-msg").css("display", "block");
                    } else {
                        $("#alert-msg").css("display", "none");
                        window.location.href = dataFromServer;
                    }
                }
            );
        }
    </script>
</head>
<body>
<div class="container">
    <%--    <c:if test="${wrongCreds != null}">--%>
    <div id="alert-msg" class="alert alert-primary" style="background: aquamarine; color: blue; display:none">
        Wrong login/password. Try again
    </div>
    <%--    </c:if>--%>
    <form action="${pageContext.servletContext.contextPath}/login" method="post">
        <div class="form-group row">
            <label for="login" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input id="login" class="form-control" type="text" name="login" placeholder="Login">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input id="password" class="form-control" type="password" name="password" placeholder="Password">
            </div>
        </div>
        <input class="btn btn-success" type="button" value="Login" onclick="return checkCreds()">
    </form>
</div>
</body>
</html>
