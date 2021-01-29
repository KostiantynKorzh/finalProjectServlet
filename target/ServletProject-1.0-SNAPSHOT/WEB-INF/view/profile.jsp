<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li><c:out value="${sessionScope.userAuth.id}"/></li>
    <li><c:out value="${sessionScope.userAuth.login}"/></li>
    <li><c:out value="${sessionScope.userAuth.password}"/></li>
    <li><c:out value="${sessionScope.userAuth.role}"/></li>
</ul>
</body>
</html>
