<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="me.project.controller.servlet.Mes"/>

<html lang="ua">
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<form method="post" action="/auth/login">
    <label><fmt:message key="login.login"/></label>
    <input type="text" name="login"/>
    <br>
    <label><fmt:message key="login.password"/></label>
    <input type="text" name="password"/>
    <br>
    <input type="submit" value="Login" name="login">
</form>
</body>
</html>
