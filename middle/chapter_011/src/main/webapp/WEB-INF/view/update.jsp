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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form action='${pageContext.servletContext.contextPath}/edit' method='post'>
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input id="name" class="form-control" type='text' name='name' value='${dto.name}'>
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input id="login" class="form-control" type='text' name='login' value='${dto.login}'>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input id="password" class="form-control" type='password' name='password' value='${dto.password}'>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input id="email" class="form-control" type='email' name='email' value='${dto.email}'>
            </div>
        </div>
        <div class="form-group row">
            <label for="country" class="col-sm-2 col-form-label">Country</label>
            <div class="col-sm-10">
                <input id="country" class="form-control" type='text' name='country' value='${dto.country}'>
            </div>
        </div>
        <div class="form-group row">
            <label for="city" class="col-sm-2 col-form-label">City</label>
            <div class="col-sm-10">
                <input id="city" class="form-control" type='text' name='city' value='${dto.city}'>
            </div>
        </div>
        <c:choose>
            <c:when test="${'admin'.equals(user.role)}">
                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2" for="role">Role</label>
                        <div id="role" class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="everyone" name="role" value="everyone"
                                    ${'everyone'.equals(dto.role)? 'checked': ''}>
                                <label class="form-check-label" for="everyone">everyone</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="admin" name="role" value="admin"
                                    ${'admin'.equals(dto.role)? 'checked': ''}>
                                <label class="form-check-label" for="admin">admin</label>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </c:when>
            <c:otherwise>
                <div class="form-group row">
                    <label for="role2" class="col-sm-2 col-form-label">Role</label>
                    <div id="role2" class="col-sm-10">
                            ${dto.role}
                    </div>
                </div>
                <input type='hidden' name='role' value='${dto.role}'>
            </c:otherwise>
        </c:choose>
        <input type='hidden' name='id' value='${dto.id}'>
        <input type='hidden' name='createdDate' value='${dto.createDate}'>
        <input class="btn btn-success" type='submit' value="Update">
    </form>
</div>
</body>
</html>
