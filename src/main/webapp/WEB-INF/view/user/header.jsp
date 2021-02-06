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
    <link rel="stylesheet" href="../style.css">
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <c:if test="${sessionScope.user!=null}">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/user/home"><fmt:message
                    key="common.header.home"/></a>
        </c:if>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <c:if test="${sessionScope.user!=null}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/profile"><fmt:message
                                key="common.header.profile"/></a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${sessionScope.user!=null}">
                        <a class="nav-link text-nowrap"
                           href="${pageContext.request.contextPath}/user/requiredTests/?sorted=id&page=1"><fmt:message
                                key="user.header.requiredTests"/></a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${sessionScope.user!=null}">
                        <a class="nav-link text-nowrap"
                           href="${pageContext.request.contextPath}/user/passedTests/?sorted=id&page=1"><fmt:message
                                key="user.header.passedTests"/></a>
                    </c:if>
                </li>
            </ul>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                    <li class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="common.header.lang"/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/lang/en">English</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/lang/ua">Українська</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <c:if test="${sessionScope.user!=null}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/auth/logout"><fmt:message
                                    key="common.header.logout"/></a>
                        </c:if>
                        <c:if test="${sessionScope.user==null}">
                            <a class="nav-link" style="width: 65px"
                               href="${pageContext.request.contextPath}/auth/logout"></a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

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
