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
</head>
<body>


<div class="divAddOutgo">
    <h2>Добавление расхода</h2>
            <form id="addCategoryform" name="addform" action="/changeOptions" method="post">
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
                    <a href="#openModal">Добавить категорию</a>
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
            <div> <input type="submit" value="Добавить" formaction="/addOutgo" formmethod="post"></div>
           </form>
</div>

<div id="openModal" class="modalDialog">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <h2>Добавление категории</h2>
        <form id="addtaskform" action="/addTask" method="post">
            <div>
            <select id="projsel" name="projsel" required>
                <option value="0" selected disabled>Выберите проект</option>
                <c:forEach  items="${descs}" var="desc">
                    <option value="${desc.key}">${desc.value}</option>
                </c:forEach>
            </select>
            </div>
            <div>
            <label for="uplvl">Категория</label><br>
            <input type="text" required id="uplvl" name="upcategory">
            </div>
            <div>
                <label for="downlvl">Дочерняя категория</label><br>
                <input type="text" id="downlvl" name="downcategory">
            </div>
            <div>
                <input type="submit" value="Добавить">
            </div>
        </form>
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
