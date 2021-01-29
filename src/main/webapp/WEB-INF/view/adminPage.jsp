<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="me.project.controller.servlet.Mes"/>


<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1><fmt:message key="admin.page"/></h1>
</body>
</html>
