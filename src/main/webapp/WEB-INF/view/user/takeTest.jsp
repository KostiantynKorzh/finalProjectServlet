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

<div class="card" style="width: 100%;">
    <div class="card-header">
        <h1 class="card-title text-center">${requestScope.test.test.title}</h1>
    </div>
    <c:forEach begin="0" end="${requestScope.test.questions.size()-1}" varStatus="loop">
        <div class="card-body" style="background-color: lightblue">
            <h3><c:out value="${requestScope.test.questions[loop.index].questionText}"/></h3>
        </div>
        <ul class="list-group list-group-flush">
            <form action="" name="${requestScope.test.questions[loop.index].id}">
                <c:forEach var="answer" items="${requestScope.test.answers}">
                    <c:if test="${answer.questionId==requestScope.test.questions[loop.index].id}">
                        <li class="list-group-item">
                            <label>
                                <input type="checkbox" name="${answer.id}">
                                <c:out value="${answer.answerText}"/>
                            </label>
                        </li>
                    </c:if>
                </c:forEach>
            </form>
        </ul>
    </c:forEach>

    <input type="submit" class="btn btn-success m-5" value="<fmt:message key="user.takeTest.submit"/>"
           onclick="submitAnswers()"/>

</div>

<div class="progress fixed-bottom">
    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
</div>

<script type="text/javascript">

    let submitted = false;

    function submitAnswers() {
        const formsCollection = document.getElementsByTagName("form");
        let answers = [];
        let qa = {
            questionId: -1,
            answers: [{
                answerId: -1,
                isChecked: false
            }]
        };
        const allQa = [];
        for (let i = 0; i < formsCollection.length; i++) {
            for (let j = 0; j < formsCollection.item(i).length; j++) {
                console.log(formsCollection.item(i).length)
                answers.push({
                    answerId: formsCollection.item(i).elements.item(j).name,
                    isChecked: formsCollection.item(i).elements.item(j).checked
                });
            }
            qa = {
                questionId: formsCollection.item(i).name,
                answers: answers
            }
            allQa.push(qa);
            answers = [];
            console.log(allQa)
        }

        let newURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname + "/complete"

        $.ajax({
            type: "POST",
            url: newURL,
            dataType: 'json',
            data: JSON.stringify(allQa)
        });

        submitted = true;

        window.location.replace("http://localhost:8080/user/home");
    }

    let deadline = ${sessionScope.deadline};
    let duration =
    ${requestScope.test.test.duration}*
    1000;

    window.addEventListener("beforeunload", function (e) {
        if (!submitted) {
            submitAnswers();
        }
    });

    $(function () {
        setInterval(function () {
            let percentage = (deadline - Date.now()) / duration * 100;
            if (percentage < 0) {
                submitAnswers();
            }
            $(".progress-bar").css("width", percentage + "%");
        }, 1000);
    });


</script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
</html>
