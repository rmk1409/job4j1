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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("select[name='country']").bind("change", function () {
                $("select[name='city']").empty();
                $.post("${pageContext.servletContext.contextPath}/cities"
                    , JSON.stringify({country: $("select[name='country']").val()})
                    , function (data) {
                        data = JSON.parse(data);
                        for (let i in data) {
                            let cur = data[i];
                            $("select[name='city']").append("<option value='" + cur + "'>" + cur + "</option>");
                        }
                    }
                );
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h3>Hi, ${user.login}</h3>
    <c:if test="${'admin'.equals(user.role)}">
        <h3>Create new user</h3>
        <form action='${pageContext.servletContext.contextPath}/create' method='post'>
            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <input id="name" class="form-control" type='text' name='name' placeholder="Name">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="login">Login</label>
                <div class="col-sm-10">
                    <input id="login" class="form-control" type='text' name='login' placeholder="Login">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="password">Password</label>
                <div class="col-sm-10">
                    <input id="password" class="form-control" type='password' name='password' placeholder="Password">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="email">Email</label>
                <div class="col-sm-10">
                    <input id="email" class="form-control" type='email' name='email' placeholder="Email">
                </div>
            </div>
            <div class="form-group row">
                <label for="country" class="col-sm-2 col-form-label">Country</label>
                <div class="col-sm-10">
                    <select id="country" class="form-control" name="country">
                        <option value="" selected></option>
                        <option value="Russia">Russia</option>
                        <option value="Europe">Europe</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label for="city" class="col-sm-2 col-form-label">City</label>
                <div class="col-sm-10">
                    <select id="city" class="form-control" name="city">
                    </select>
                </div>
            </div>
            <fieldset class="form-group">
                <div class="row">
                    <label class="col-form-label col-sm-2" for="sex">Role</label>
                    <div id="sex" class="col-sm-10">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="everyone" name="role" value="everyone"
                                   checked>
                            <label class="form-check-label" for="everyone">everyone</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="admin" name="role" value="admin">
                            <label class="form-check-label" for="admin">admin</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <input type='submit' class="btn btn-success" value="Create new User">
        </form>
    </c:if>
    <button class="btn btn-info" onclick="window.location.href = '${pageContext.servletContext.contextPath}/logout';">
        Logout
    </button>
    <br>
    <table class="table table-hover table-striped">
        <caption>List of users</caption>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Login</th>
            <th scope="col">Password</th>
            <th scope="col">Email</th>
            <th scope="col">Country</th>
            <th scope="col">City</th>
            <th scope="col">Role</th>
            <th scope="col">Created</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="current">
            <tr>
                <th scope="row"><c:out value="${current.id}"/></th>
                <td><c:out value="${current.name}"/></td>
                <td><c:out value="${current.login}"/></td>
                <td><c:out value="${current.password}"/></td>
                <td><c:out value="${current.email}"/></td>
                <td><c:out value="${current.country}"/></td>
                <td><c:out value="${current.city}"/></td>
                <td><c:out value="${current.role}"/></td>
                <td><c:out value="${current.createDate}"/></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${current.id}">
                        <button class="btn btn-warning" ${'admin'.equals(user.role) || current.id == user.id ? '' : 'disabled'}>
                            Edit
                        </button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                        <input type="hidden" name="id" value="${current.id}">
                        <button class="btn btn-danger" ${'admin'.equals(user.role)? '' : 'disabled'}>Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
