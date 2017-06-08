<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css"/>
</head>
<body>
<header>
    <jsp:include page="menu-login.jsp"></jsp:include>
</header>
<c:choose>
    <c:when test="${loginError != null}">
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error!</strong> Invalid Login Credentials
        </div>
    </c:when>
</c:choose>
<br/>
<div class="container">
    <div class="row">
        <div class="jumbotron col-sm-12">
            <form method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Log In">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js}"></script>
</body>
</html>