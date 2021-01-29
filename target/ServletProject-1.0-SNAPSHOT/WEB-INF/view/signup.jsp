<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<form method="post" action="/auth/signup">

    <div>
        <label>First Name</label>
        <label><input type="text" name="firstName"></label>
    </div>
    <div>
        <label>Last Name</label>
        <label><input type="text" name="lastName"></label>
    </div>
    <div>
        <label>Email</label>
        <label><input type="text" name="email"></label>
    </div>
    <div>
        <label>Password</label>
        <label><input type="text" name="password"></label>
    </div>
    <input type="submit" value="OK" name="OK"/>

</form>

</body>
</html>
