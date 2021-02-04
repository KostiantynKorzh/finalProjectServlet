<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="mes"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

<%@include file="header.jsp" %>

<div class="card text-center" style="width: 100%;">
    <div class="card-body">
        <h1 class="card-title" style="height: 100px"><c:out value="${requestScope.userToAdd}"/></h1>
        <hr/>

        <div class="container">

            <h1><fmt:message key="admin.addTestsToUser.availableTests"/></h1>
            <table class="table" id="availableTests">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col"><fmt:message key="admin.allTests.title"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.subject"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.difficulty"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.duration"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="test" items="${requestScope.availableTests}">
                    <tr>
                        <th><c:out value="${test.id}"/></th>
                        <th><c:out value="${test.title}"/></th>
                        <th><c:out value="${test.subject}"/></th>
                        <th><c:out value="${test.difficulty}"/></th>
                        <th><c:out value="${test.duration}"/></th>
                        <th>
                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/admin/users/addTests/${requestScope.userId}/add/${test.id}"
                               role="button">Add</a>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <hr/>

            <h1><fmt:message key="admin.addTestsToUser.requiredTests"/></h1>

            <table class="table" id="requiredTests">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col"><fmt:message key="admin.allTests.title"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.subject"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.difficulty"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.duration"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="test" items="${requestScope.requiredTests}">
                    <tr>
                        <th><c:out value="${test.id}"/></th>
                        <th><c:out value="${test.title}"/></th>
                        <th><c:out value="${test.subject}"/></th>
                        <th><c:out value="${test.difficulty}"/></th>
                        <th><c:out value="${test.duration}"/></th>
                        <th>
                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/admin/users/addTests/${requestScope.userId}/remove/${test.id}"
                               role="button">Remove</a>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <hr/>

            <h1><fmt:message key="admin.addTestsToUser.passedTests"/></h1>

            <table class="table" id="passedTests">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col"><fmt:message key="admin.allTests.title"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.subject"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.difficulty"/></th>
                    <th scope="col"><fmt:message key="admin.allTests.duration"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="result" items="${requestScope.passedTests}">
                    <tr>
                        <th><c:out value="${result.test.id}"/></th>
                        <th><c:out value="${result.test.title}"/></th>
                        <th><c:out value="${result.test.subject}"/></th>
                        <th><c:out value="${result.test.difficulty}"/></th>
                        <th><c:out value="${result.test.duration}"/></th>
                        <th><c:out value="${result.score}"/></th>
                        <th><c:out value="${result.passTimestamp}"/></th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>
