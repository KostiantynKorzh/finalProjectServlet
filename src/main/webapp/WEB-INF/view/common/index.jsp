<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="me.project.controller.servlet.Mes"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user.role.getName().equals('ADMIN')}">
        <%@include file="../admin/header.jsp" %>
    </c:when>
    <c:when test="${!sessionScope.user.role.getName().equals('ADMIN')}">
        <%@include file="../user/header.jsp" %>
    </c:when>
</c:choose>
<h2>
    Yeap, it's servlet time
</h2>

<a href="/auth/login">Login</a>
<a href="/auth/signup">Signup</a>
<a href="/auth/logout">Logout</a>

</body>
</html>
