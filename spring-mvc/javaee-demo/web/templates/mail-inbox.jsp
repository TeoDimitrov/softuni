<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>WizMail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mail.css"/>
</head>
<body>
<header>
    <jsp:include page="menu-mail-inbox.jsp"></jsp:include>
</header>
<main>
    <div class="container-fluid">
        <div class="row">
            <nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
                <c:set value="${newMessages}" var="newMessages"/>
                <ul class="nav nav-pills flex-column red">
                    <a id="compose" class="btn btn-primary" href="/mail/new">Compose</a>
                    <li class="nav-item">
                        <a class="nav-link active" href="/mail/inbox">Inbox
                            <c:choose>
                                <c:when test="${newMessages gt 0}">
                                    <span>(${newMessages})</span>
                                </c:when>
                            </c:choose>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mail/sent">Sent </a>
                    </li>
                    <li class="nav-item">
                        <c:set value="${drafts}" var="drafts"/>
                        <a class="nav-link" href="/mail/drafts">Drafts
                            <c:choose>
                                <c:when test="${drafts gt 0}">
                                    <span>(${drafts})</span>
                                </c:when>
                            </c:choose>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mail/trash">Trash </a>
                    </li>
                </ul>
            </nav>
        </div>
        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3 mail-area">
            <table class="table table-inverse">
                <thead>
                <tr>
                    <div class="row">
                        <th>Subject</th>
                        <th>Message</th>
                        <th>A</th>
                        <th>Date</th>
                        <th></th>
                    </div>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emails}" var="email">
                    <tr>
                        <div class="row">
                            <div class="col-sm-3">
                                <td>
                                    <a href="/mail/received/${email.id}">
                                        <c:choose>
                                            <c:when test="${email.isRead()}">
                                                ${email.subject}
                                            </c:when>
                                            <c:otherwise>
                                                <b>
                                                        ${email.subject}
                                                </b>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </td>
                            </div>
                            <div class="col-sm-6">
                                <td>
                                    <c:choose>
                                        <c:when test="${email.isRead()}">
                                            <a href="/mail/received/${email.id}">${fn:substring(email.message, 0, 50)}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <b>
                                                <a href="/mail/received/${email.id}">${fn:substring(email.message, 0, 50)}</a>
                                            </b>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <a href="/mail/received/${email.id}">
                                        <c:choose>
                                            <c:when test="${email.isRead()}">
                                                <c:choose>
                                                    <c:when test="${empty email.attachment}">
                                                        N
                                                    </c:when>
                                                    <c:otherwise>
                                                        Y
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${empty email.attachment}">
                                                        <b> N </b>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <b> Y </b>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <c:choose>
                                        <c:when test="${email.isRead()}">
                                            <a href="/mail/received/${email.id}"><fmt:formatDate pattern="yy-MMM-dd"
                                                                                                value="${email.sentOn}"/></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/mail/received/${email.id}"><b><fmt:formatDate pattern="yy-MMM-dd"
                                                                                                   value="${email.sentOn}"/></b></a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <a href="/mail/trash/${email.id}" class="btn btn-danger">X</a>
                                </td>
                            </div>
                        </div>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</main>
</body>
<script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js}"></script>
</html>
