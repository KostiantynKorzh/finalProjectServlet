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
            <th scope="col">
                <a href="${pageContext.request.contextPath}/user/requiredTests/?sorted=title&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.title"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/user/requiredTests/?sorted=subject&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.subject"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/user/requiredTests/?sorted=difficulty&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.difficulty"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/user/requiredTests/?sorted=duration&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.duration"/>
                </a>
            </th>
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
                       role="button"><fmt:message key="user.requiredTests.take"/></a>
                </th>
                <th>
                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/user/requiredTests/pass/${test.id}"
                       role="button"><fmt:message key="user.requiredTests.pass"/></a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/user/requiredTests/?sorted=${requestScope.parameter}&page=${requestScope.page-1}"><</a>
            </li>
            <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index==requestScope.page}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/user/requiredTests/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/user/requiredTests/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/user/requiredTests/?sorted=${requestScope.parameter}&page=${requestScope.page+1}">></a>
            </li>
        </ul>
    </nav>

</div>

</body>
</html>
