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

<div class="card" style="width: 100%;" id="card">
    <div class="card-header">
        <h1 class="card-title text-center">${requestScope.title}</h1>
        <h4 class="card-subtitle text-center">${requestScope.subject}</h4>
        <h4 class="card-subtitle text-center">${requestScope.difficulty}</h4>
        <h4 class="card-subtitle text-center">${requestScope.duration}</h4>
        <button id="addBtn" class="btn btn-success">Add Question</button>
        <%--        <button id="addNewAnswerBtn-1" class="btn btn-success">Add Answer</button>--%>
    </div>
    <div id="qaBlock">
        <div class="card-body" id="textCard">
            <%--            <span><button class="btn btn-success" id="addNewAnswerBtn-1">+</button></span>--%>
            <div class="input-group">
                <%--                <div class="input-group-prepend">--%>
                <%--                    <button id="deleteQuestionBtn" class="btn btn-danger">-</button>--%>
                <%--                </div>--%>
                <textarea class="form-control" style="background-color: lightblue; border: solid coral 3px"
                          aria-label="With textarea" placeholder="Your Question"></textarea>
                <div class="input-group-append">
                    <button class="btn btn-success addNewAnswerBtn">+</button>
                </div>
            </div>
        </div>
        <ul class="list-group list-group-flush">
            <form action="" id="1">
                <li class="list-group-item" id="1-1">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                 <input type="checkbox">
                                </span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea"></textarea>
                        <%--                        <div class="input-group-append">--%>
                        <%--                            <button class="btn btn-danger deleteAnswerBtn" type="button">-</button>--%>
                        <%--                        </div>--%>
                    </div>
                </li>
            </form>
        </ul>
    </div>
    <input type="submit" value="Submit Test" onclick="submitTest()"/>

</div>

<script>

    let counter = 2;

    function addNewQuestion() {
        let formTemp = '<form action="" id=' + counter + '>';
        let btnTemp = '<span><button class="btn btn-success" id="addNewAnswerBtn-' + (counter) + '">+</button></span>';
        let deleteBtn = '<button class="btn btn-danger" id="deleteQuestionBtn-' + (counter) + '">-</button>';
        $("#qaBlock").append(
            '<div id="qaBlock">' +
            '<div class="input-group">' +
            // '<div class="input-group-prepend">' +
            // deleteBtn +
            // '</div>' +
            '<textarea class="form-control" style="background-color: lightblue; border: solid coral 3px"' +
            'aria-label="With textarea" placeholder="Your Question"></textarea>' +
            '<div class="input-group-append">' +
            '<button class="btn btn-success addNewAnswerBtn">+</button>' +
            '</div>' +
            '</div>' +
            '<ul class="list-group list-group-flush">' +
            formTemp +
            '<li class="list-group-item">' +
            '<div class="input-group">' +
            '<div class="input-group-prepend">' +
            '<span class="input-group-text">' +
            '<input type="checkbox">' +
            '</span>' +
            '</div>' +
            '<textarea class="form-control" aria-label="With textarea"></textarea>' +
            // '<div class="input-group-append">' +
            // '<button class="btn btn-danger deleteAnswerBtn" type="button" >-</button>' +
            // '</div>' +
            '</div>' +
            '</li>' +
            '</form>' +
            '</ul>' +
            '</div>');
        counter++;
    }

    function addNewAnswer() {
        let form = document.getElementById((counter - 1));
        let checkboxId = (counter - 1) + '-' + (Array.from(form.querySelectorAll("input")).length + 1);
        let liTemp = '<li class="list-group-item" id="' + checkboxId + '">';
        $('#' + (counter - 1)).append(
            liTemp +
            '<div class="input-group">' +
            '<div class="input-group-prepend">' +
            '<span class="input-group-text">' +
            '<input type="checkbox">' +
            '</span>' +
            '</div>' +
            '<textarea class="form-control" aria-label="With textarea"/>' +
            // '<div class="input-group-append">' +
            // '<button class="btn btn-danger deleteAnswerBtn" type="button">-</button>' +
            // '</div>' +
            '</div>' +
            '</li>');
    }

    function deleteAnswer() {
        this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
    }


    $(document).ready(function () {

        $('#addBtn').click(function () {
            $(".addNewAnswerBtn").attr('disabled','disabled');
            addNewQuestion();
            $(".addNewAnswerBtn").on('click', addNewAnswer);
            $(".deleteAnswerBtn").on('click', deleteAnswer);
        });

        $('.addNewAnswerBtn').click(function () {
            addNewAnswer();
        });

        $('.deleteAnswerBtn').click(deleteAnswer);

    });


    function submitTest() {
        const formsCollection = document.getElementsByTagName("form");
        let answers = [];
        let qa = {
            testTitle: '',
            testSubject: '',
            testDifficulty: '',
            testDuration: 0,
            questionText: '',
            answers: [{
                answerText: '',
                isCorrect: false
            }]
        };
        const allQa = [];

        // 3 is number of elements in qa block: checkbox, text area, button
        for (let i = 0; i < formsCollection.length; i++) {
            for (let j = 0; j < formsCollection.item(i).length / 3; j++) {

                answers.push({
                    answerText: formsCollection.item(i).elements.item(1 + 3 * j).value,
                    isCorrect: formsCollection.item(i).elements.item(3 * j).checked
                });
            }
            qa = {
                testTitle: '${requestScope.title}',
                testSubject: '${requestScope.subject}',
                testDifficulty: '${requestScope.difficulty}',
                testDuration: '${requestScope.duration}',

                questionText: formsCollection.item(i).parentNode.parentNode.children.item(0).getElementsByClassName('form-control').item(0).value,
                answers: answers
            }
            allQa.push(qa);
            answers = [];
        }

        console.log(allQa)

        let newURL = window.location.protocol + "//" + window.location.host + "" + window.location.pathname + "/complete"
        console.log(newURL)

        $.ajax({
            type: "POST",
            url: newURL,
            dataType: 'json',
            data: JSON.stringify(allQa)
        });
        window.location.replace("http://localhost:8080/admin/home");
    }


</script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
</html>
