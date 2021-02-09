<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="mes"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp" %>

<div class="container">
    <table class="table" id="table">
        <thead>
        <tr>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/users/?sorted=id&page=${requestScope.page}">
                    id
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/users/?sorted=first_name&page=${requestScope.page}">
                    <fmt:message key="admin.allUsers.firstName"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/users/?sorted=last_name&page=${requestScope.page}">
                    <fmt:message key="admin.allUsers.lastName"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/users/?sorted=email&page=${requestScope.page}">
                    <fmt:message key="admin.allUsers.email"/>
                </a>
            </th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <th><c:out value="${user.id}"/></th>
                <th><c:out value="${user.firstName}"/></th>
                <th><c:out value="${user.lastName}"/></th>
                <th><c:out value="${user.login}"/></th>
                <th><a class="btn btn-success"
                       href="${pageContext.request.contextPath}/admin/users/addTests/${user.id}">
                    <fmt:message key="admin.allUsers.addTests"/></a></th>
                <th>
                    <button type="button" class="btn btn-primary"
                            data-toggle="modal" data-target="#editModal" data-id="${user.id}">
                        <fmt:message key="admin.allUsers.edit"/>
                    </button>
                </th>
                <th><a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/users/delete/${user.id}">
                    <fmt:message key="admin.allUsers.delete"/></a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/admin/users/?sorted=${requestScope.parameter}&page=${requestScope.page-1}"><</a>
            </li>
            <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index==requestScope.page}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/admin/users/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/admin/users/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/admin/users/?sorted=${requestScope.parameter}&page=${requestScope.page+1}">></a>
            </li>
        </ul>
    </nav>

</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/users/edit">
                    <div class="form-group has-error">
                        <div class="mb-3">
                            <label for="firstName" class="control-label">First Name:</label>
                            <input type="text" class="form-control" id="firstName" name="firstName">
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="col-form-label">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" name="lastName">
                        </div>
                        <input hidden type="text" name="id" id="id"/>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" id="saveEdit" data-id="">Save changes</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#editModal').on('submit', function (e) {
        const firstName = $('#firstName');
        if (!firstName.val()) {
            e.preventDefault();
        }
        const lastName = $('#lastName');
        if (!lastName.val()) {
            e.preventDefault();
        }

    });
</script>


</body>
</html>
