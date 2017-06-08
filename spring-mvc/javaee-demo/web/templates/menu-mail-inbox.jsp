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
                <div class="col-sm-7">
                    <div class="collapse navbar-collapse" id="navbarSearch">
                        <form class="form-inline">
                            <input class="form-control mr-sm-2" name="search" placeholder="Search" type="text"/>
                            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="col-sm-2">
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