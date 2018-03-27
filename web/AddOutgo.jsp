<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление расхода</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/modalDialog.css"/>
</head>
<body>

<table align="center" border="1">
    <caption>Добавление расхода</caption>
        <form id="addform" name="addform" action="/changeOptions" method="post">
            <tr><td>
            <select id="first" name="firstchoice" required onchange="this.form.submit()">
                        <option value="0" selected disabled>Выберите расход</option>
                        <c:forEach  items="${descs}" var="desc">
                            <option value="${desc.key}"
                                ${desc.key == indexes[0] ? 'selected' : ''}
                            >${desc.value}
                            </option>
                        </c:forEach>
                    </select>
            </td></tr>
            <tr><td>
                        <select  id="second" name="secondchoice" required onchange="this.form.submit()">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${seconds}"  var="sec">
                            <option value="${sec.key}"
                                ${sec.key == indexes[1] ? 'selected' : ''}>${sec.value}
                            </option>
                        </c:forEach>
                    </select>
                    <a href="#openModal">Добавить категорию</a>
            </td></tr>
            <tr><td>
                <select id="third" name="thirdchoice">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${thirds}" var="third">
                            <option value="${third.key}"
                                ${third.key == indexes[2] ? 'selected' : ''}>${third.value}
                            </option>
                        </c:forEach>
                    </select>
            </td></tr>
            <tr><td><input name="sum" type="number" value="${sum}" size="5" placeholder="1.0" step="0.01" min="0"></td></tr>
            <tr><td><input type="submit" value="Добавить" formaction="/addOutgo}" formmethod="post"></td></tr>
        </form>
</table>

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
</body>
</html>
