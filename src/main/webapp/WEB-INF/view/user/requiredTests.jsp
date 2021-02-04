<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="me.project.controller.servlet.Mes"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<%@include file="header.jsp" %>
<div class="container">
    <table class="table" id="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Subject</th>
            <th scope="col">Difficulty</th>
            <th scope="col">Duration</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${requestScope.requiredTests}">
            <tr>
                <th><c:out value="${test.title}"/></th>
                <th><c:out value="${test.subject}"/></th>
                <th><c:out value="${test.difficulty}"/></th>
                <th><c:out value="${test.duration}"/></th>
                <th>
                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/user/requiredTests/take/${test.id}"
                       role="button">Take</a>
                </th>
                <th>
                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/user/requiredTests/pass/${test.id}"
                       role="button">Pass</a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>
