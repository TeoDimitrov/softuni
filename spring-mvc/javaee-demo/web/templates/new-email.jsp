<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Email</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css"/>
</head>
<body>
<header>
    <jsp:include page="menu-empty.jsp"></jsp:include>
</header>
<c:forEach items="${constraintViolations}" var="violation">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Error!</strong> ${violation.getMessage()}
    </div>
</c:forEach>
<br/>
<div class="container">
    <div class="row">
        <div class="jumbotron col-sm-12">
            <form method="post">
                <div class="form-group">
                    <label>Recipients</label>
                    <input name="recipientsUsername" type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Subject</label>
                    <input name="subject"  type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label>Message</label>
                    <textarea name="message" rows="5" type="text" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>Attachment</label>
                    <input name="attachment" type="text" class="form-control" placeholder="URL">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="Send">
                    <input class="btn btn-primary" formaction="/mail/draft" type="submit" value="Save">
                    <a href="/mail/inbox" class="btn btn-primary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js}"></script>
</body>
</html>