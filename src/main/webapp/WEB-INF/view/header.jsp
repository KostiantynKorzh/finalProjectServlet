<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div class="inline">
    <div class="inline">
        <form method="post" action="/lang">
            <%--    <select name="lang" id="lang">--%>
            <%--        <option value="ua">ua</option>--%>
            <%--        <option value="en">en</option>--%>
            <%--    </select>--%>
            <input type="submit" value="Change Lang" name="change">
        </form>
    </div>
    <div class="inline">
        <c:if test="${sessionScope.userAuth!=null}">
            <a href="/profile">Profile</a>
        </c:if>
    </div>
</div>
</body>
</html>
