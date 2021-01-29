<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>HOME PAGE</h1>

<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>
            <h3><c:out value="${user.id}"/></h3>
            <h3><c:out value="${user.name}"/></h3>
            <h3><c:out value="${user.age}"/></h3>
            <form method="get" action="/edit">
                <input type="submit" value="Edit" name="edit">
                <input type="text"  name="newName">
                <input type="number" name="id" hidden value="${user.id}">
            </form>
            <form method="post" action="/edit">
                <input type="submit" value="Delete" name="delete">
                <input type="number" name="id" hidden value="${user.id}">
            </form>
        </li>
    </c:forEach>
</ul>

<h2>Create new</h2>

<form method="post" action="/home">

    <label><input type="text" name="name"></label>
    <label><input type="text" name="age"></label>

    <input type="submit" value="OK" name="OK"/>

</form>

</body>
</html>
