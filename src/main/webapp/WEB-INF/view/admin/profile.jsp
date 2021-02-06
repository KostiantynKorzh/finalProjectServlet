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

<div class="card text-center border-0" style="width: 100%;">
    <div class="card-body">
        <h1 class="card-title" style="height: 100px">
            <c:out value="${requestScope.userToInfo.firstName}"/> <c:out value="${requestScope.userToInfo.lastName}"/>
        </h1>
        <h3 class="card-subtitle" style="margin-bottom: 100px"><c:out value="${requestScope.userToInfo.email}"/></h3>

        <div class="container" style="width: 60%">

            <div class="row">
                <div class="col-4">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action active" id="overall-users-list"
                           data-toggle="list"
                           href="#list-home" role="tab" aria-controls="home"><fmt:message
                                key="admin.profile.overallUsers"/></a>
                        <a class="list-group-item list-group-item-action" id="overall-tests-list" data-toggle="list"
                           href="#list-profile" role="tab" aria-controls="profile"><fmt:message
                                key="admin.profile.overallTests"/></a>
                    </div>
                </div>
                <div class="col-8">
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="list-home" role="tabpanel"
                             aria-labelledby="overall-list">
                            <h1 style="font-size: 80px"><c:out value="${requestScope.overallUsers}"/></h1>
                        </div>
                        <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="avg-list">
                            <h1 style="font-size: 80px"><c:out value="${requestScope.overallTests}"/></h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>
