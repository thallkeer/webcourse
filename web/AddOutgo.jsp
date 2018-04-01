<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление расхода</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/modalDialog.css"/>
    <%--<script src="resources/sendparams.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
        }

        .addOutgoform {
            max-width: 230px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .addOutgoform input[type="text"],
        .addOutgoform input[type="text"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>
</head>
<body>

            <form class="addOutgoform" id="addCategoryform" name="addform" action="/changeOptions" method="post">
                <div><h3>Добавление расхода</h3></div>
            <div>
                <select id="first" name="firstchoice" required onchange="this.form.submit()" >
                    <option value="0" selected disabled>Выберите расход</option>
                    <c:forEach  items="${descs}" var="desc">
                        <option value="${desc.key}"
                            ${desc.key == indexes[0] ? 'selected' : ''}
                        >${desc.value}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                        <select  id="second" name="secondchoice" required onchange="this.form.submit()">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${seconds}"  var="sec">
                            <option value="${sec.key}"
                                ${sec.key == indexes[1] ? 'selected' : ''}>${sec.value}
                            </option>
                        </c:forEach>
                    </select>
            </div>

            <div><select id="third" name="thirdchoice">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${thirds}" var="third">
                            <option value="${third.key}"
                                ${third.key == indexes[2] ? 'selected' : ''}>${third.value}
                            </option>
                        </c:forEach>
            </select></div>

            <div><input class="inputAcc" name="sum" type="number" value="${sum}" step="0.01" min="0"></div>
                <div> <input type="submit" value="Добавить" formaction="/addOutgo" formmethod="post">
                      <a href="#openModal" class="hrefAddCategory">Добавить категорию</a>
                </div>
</form>


<div id="openModal" class="modalDialog">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <%--<div >  style="margin: 0 auto 20px;"--%>
        <div><h3>Добавление категории</h3></div>
        <div>
        <form id="addtaskform" action="/addTask" method="post">
            <div>
            <select class="projsel" id="projsel" name="projsel" required>
                <option value="0" selected disabled>Выберите проект</option>
                <c:forEach  items="${descs}" var="desc">
                    <option value="${desc.key}">${desc.value}</option>
                </c:forEach>
            </select>
            </div>
            <div>
                <p>Категория</p>
                <input class="inputAddCat" type="text" required id="uplvl" name="upcategory">
            </div>
            <div>
                <p>Дочерняя категория</p>
                <input class="inputAddCat" type="text" id="downlvl" name="downcategory">
            </div>
            <div>
                <input class="submitAdd" type="submit" value="Добавить">
            </div>
        </form>
        </div>
    </div>
</div>

<%--<div id="test" onclick="$('#test').hide()">Test</div>--%>

<script>
    // $(document).ready(function () {
    //     $("#addCategoryform").submit(function () {
    //         $.ajax({
    //             type: "POST",
    //             url: "/changeOptions",
    //             data: $(this).serialize()
    //         }).done(function () {
    //             alert("ABC");
    //         });
    //         return false;
    //     });
    // });
    function send() {
        $.ajax({
            type: "GET",
            url: "/changeOptions",
            data: { firstchoice : $('#first').val()}
        });
    }

</script>


</body>
</html>
