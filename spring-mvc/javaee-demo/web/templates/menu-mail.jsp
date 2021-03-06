<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Mail</title>
</head>
<header>
    <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 hidden-xs-down">
                    <a class="navbar-brand" href="/mail/inbox">WizMail</a>
                </div>
                <div class="col-sm-2 offset-7">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item">
                            <c:set var="user" value="${sessionScope.currentUser}"></c:set>
                            <a class="nav-link" href="/logout">Log Out(${user.getFullName()})</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>
</html>