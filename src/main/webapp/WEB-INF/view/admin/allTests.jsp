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

    <button type="button" class="btn btn-primary"
            data-toggle="modal" data-target="#createTestModal">Create Test
    </button>

    <table class="table" id="table">
        <thead>
        <tr>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/tests/?sorted=id&page=${requestScope.page}">
                    id
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/tests/?sorted=title&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.title"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/tests/?sorted=subject&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.subject"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/tests/?sorted=difficulty&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.difficulty"/>
                </a>
            </th>
            <th scope="col">
                <a href="${pageContext.request.contextPath}/admin/tests/?sorted=duration&page=${requestScope.page}">
                    <fmt:message key="admin.allTests.duration"/>
                </a>
            </th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${requestScope.tests}">
            <tr>
                <th><c:out value="${test.id}"/></th>
                <th><c:out value="${test.title}"/></th>
                <th><c:out value="${test.subject}"/></th>
                <th><c:out value="${test.difficulty}"/></th>
                <th><c:out value="${test.duration}"/></th>
                <th><a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/admin/tests/edit/${test.id}">
                    <fmt:message key="admin.allUsers.edit"/></a>
                <th>
                    <a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/admin/tests/delete/${test.id}">
                        <fmt:message key="admin.allUsers.delete"/></a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/admin/tests/?sorted=${requestScope.parameter}&page=${requestScope.page-1}"><</a>
            </li>
            <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index==requestScope.page}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/admin/tests/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/admin/tests/?sorted=${requestScope.parameter}&page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/admin/tests/?sorted=${requestScope.parameter}&page=${requestScope.page+1}">></a>
            </li>
        </ul>
    </nav>
</div>


<div class="modal fade" id="createTestModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create New Test</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/createTest">
                    <div class="form-group has-error">
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="title">Title:</label>
                                </div>
                                <input type="text" class="form-control" id="title" name="title"
                                       required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="subject">Subject:</label>
                                </div>
                                <select class="custom-select" id="subject" name="subject">
                                    <option value="MATH" selected>MATH</option>
                                    <option value="ENGLISH">ENGLISH</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="difficulty">Difficulty:</label>
                                </div>
                                <select class="custom-select" id="difficulty" name="difficulty">
                                    <option value="EASY" selected>EASY</option>
                                    <option value="MEDIUM">MEDIUM</option>
                                    <option value="HARD">HARD</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="duration">Duration:</label>
                                </div>
                                <input type="text" class="form-control" id="duration" name="duration"
                                       required pattern="[0-9]+">
                            </div>
                        </div>
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

</body>
</html>
