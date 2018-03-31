<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Задачи</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/modalDialog.css"/>
</head>
<body>
<div>
<table align="center" border="1">
    <caption>Справочник проектов и задач</caption>
    <thead>
    <th>Номер задачи</th>
    <th>Номер верхней категории</th>
    <th>Описание</th>
    <th>Редактировать</th>
    <th>Удалить</th>
    </thead>
    <c:forEach items="${tasks}" var="task">
    <tbody>
    <tr>
        <td><c:out value="${task.task_id}"/></td>
        <td><c:out value="${task.ptask_id}"/></td>
        <td><c:out value="${task.description}"/></td>
        <td><a href="/editTask?task_id=${task.task_id}">${task.ptask_id==0 ? '' :'Редактировать'}</a></td>
        <td><a href="/deleteTask?task_id=${task.task_id}">${task.ptask_id==0 ? '' :'Удалить'}</a></td>
    </tr>
    </tbody>
    </c:forEach>
</table>
</div>
<div>
    <a href="#openModal" class="btnAdd">Добавить проект</a>
    <a href="#categoryModal" class="btnAdd">Добавить статью расходов</a>
    <a href="/employees" class="href">Сотрудники</a>
    <div><br></div>
</div>

<div id="openModal" class="modalDialog">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <h2>Добавление проекта</h2>
        <div class="divAddUser">
            <form action="/addProject" method="post">
                <div>
                    <input class="inputAdd" required  type="text" placeholder=" Название проекта" name="title"/>
                </div>
                <div>
                    <label for="org">Категория</label><br>
                    <select name="org" id="org">
                        <option selected value="ООО 'ЗНАК-63'">ООО "ЗНАК-63"</option>
                        <option value='ООО "НПЦ" "ИТС"'>ООО "НПЦ" "ИТС"</option>
                    </select>
                </div>
                <div class="divLvl">
                    <p>Архивный проект:<input class="inputAdd" type="checkbox" name="archival"></p>
                </div>
                <div class="divSubmit">
                    <input class="inputAdd" type="submit" value="Добавить">
                </div>
            </form>
        </div>
    </div>
</div>




<div id="categoryModal" class="modalDialog">
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
                <input class="inputAdd" type="text" required id="uplvl" name="upcategory">
            </div>
            <div>
                <label for="downlvl">Дочерняя категория</label><br>
                <input class="inputAdd" type="text" id="downlvl" name="downcategory">
            </div>
            <div>
                <input type="submit" value="Добавить">
            </div>
        </form>
    </div>
</div>
</body>
</html>
