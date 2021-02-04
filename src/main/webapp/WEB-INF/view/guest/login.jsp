<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="mes" var="b"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<style>
    .login-form {
        width: 340px;
        margin: 50px auto;
        font-size: 15px;
    }

    .login-form form {
        margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }

    .login-form h2 {
        margin: 0 0 15px;
    }
</style>
<body>
<%@include file="../user/header.jsp" %>

<div class="login-form">
    <form method="post" action="${pageContext.request.contextPath}/auth/login">
        <h2 class="text-center"><fmt:message key="login.welcome" bundle="${b}"/></h2>
        <div class="form-group">
            <input type="email" class="form-control" name="login"
                   placeholder="<fmt:message key="login.login" bundle="${b}"/>" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password"
                   placeholder="<fmt:message key="login.password" bundle="${b}"/>" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="login.submit" bundle="${b}"/></button>
        </div>
    </form>
    <p class="text-center"><a href="${pageContext.request.contextPath}/auth/signup"><fmt:message key="login.signup" bundle="${b}"/></a></p>
</div>

</body>
</html>
