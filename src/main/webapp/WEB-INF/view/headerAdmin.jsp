<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div class="inline">
    <div class="inline">
        <form method="post" action="/lang">
            <input type="submit" value="Change Lang" name="change">
        </form>
    </div>
    <div class="inline">
        <a href="/admin/users">Users</a>
    </div>
    <div class="inline">
        <a href="/admin/tests">Tests</a>
    </div>
</div>
</body>
</html>
