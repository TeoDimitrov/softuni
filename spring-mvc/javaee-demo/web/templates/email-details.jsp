<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Email Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css"/>
</head>
<body>
<header>
    <jsp:include page="menu-empty.jsp"></jsp:include>
</header>
<br/>
<div class="container">
    <div class="row">
        <c:set value="${email}" var="email"></c:set>
        <div class="jumbotron col-sm-12">
            <form method="post">
                <div class="form-group">
                    <label>Sender</label>
                    <input name="sender" type="text" class="form-control" readonly value="${email.senderUsername}">
                </div>
                <div class="form-group">
                    <label>Recipients</label>
                    <input name="recipients" type="text" class="form-control" readonly value="${email.recipientsUsername}">
                </div>
                <div class="form-group">
                    <label>Subject</label>
                    <input name="subject"  type="text" class="form-control" readonly value="${email.subject}"/>
                </div>
                <div class="form-group">
                    <label>Message</label>
                    <textarea name="message" rows="5" type="text" class="form-control" readonly>${email.message}</textarea>
                </div>
                <div class="form-group">
                    <label>Attachment</label>
                    <a href="${email.attachment}" name="attachment" type="text" class="form-control" readonly>${email.attachment}</a>
                </div>
                <div class="form-group">
                    <a href="/mail/inbox" class="btn btn-primary">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js}"></script>
</body>
</html>