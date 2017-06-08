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
    <jsp:include page="menu-mail.jsp"></jsp:include>
</header>
<main>
    <div class="container-fluid">
        <div class="row">
            <nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
                <c:set value="${newMessages}" var="newMessages"/>
                <ul class="nav nav-pills flex-column red">
                    <a id="compose" class="btn btn-primary" href="/mail/new">Compose</a>
                    <li class="nav-item">
                        <a class="nav-link" href="/mail/inbox">Inbox
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
                        <a class="nav-link active" href="/mail/drafts">Drafts
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
                                    <a href="/mail/sent/${email.id}">
                                            ${email.subject}
                                    </a>
                                </td>
                            </div>
                            <div class="col-sm-6">
                                <td>
                                    <a href="/mail/sent/${email.id}">${fn:substring(email.message, 0, 50)}</a>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <a href="/mail/sent/${email.id}">
                                        <c:choose>
                                            <c:when test="${empty email.attachment}">
                                                N
                                            </c:when>
                                            <c:otherwise>
                                                Y
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <a href="/mail/sent/${email.id}"><fmt:formatDate pattern="yy-MMM-dd"
                                                                                        value="${email.sentOn}"/></a>
                                </td>
                            </div>
                            <div class="col-sm-1">
                                <td>
                                    <a href="/mail/send/${email.id}" class="btn btn-success">Send</a>
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
