<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="mes"/>

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
            <th scope="col">id</th>
            <th scope="col"><fmt:message key="admin.allTests.title"/></th>
            <th scope="col"><fmt:message key="admin.allTests.subject"/></th>
            <th scope="col"><fmt:message key="admin.allTests.difficulty"/></th>
            <th scope="col"><fmt:message key="admin.allTests.duration"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${requestScope.tests}">
            <tr>
                <th><c:out value="${test.id}"/></th>
                <th><c:out value="${test.title}"/></th>
                <th><c:out value="${test.subject}"/></th>
                <th><c:out value="${test.difficulty}"/></th>
                <th><c:out value="${test.duration}"/></th>
                <th><a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/admin/tests/edit/${test.id}">
                    <fmt:message key="admin.allUsers.edit"/></a>
                <th>
                <a class="btn btn-danger"
                   href="${pageContext.request.contextPath}/admin/tests/delete/${test.id}">
                    <fmt:message key="admin.allUsers.delete"/></a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
