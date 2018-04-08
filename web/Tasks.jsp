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
    <div style="width: 210px">
        <a href="#close" title="Закрыть" class="close">X</a>
        <div><h3>Добавление проекта</h3></div>
        <div class="divAddUser">
            <form action="/addProject" method="post">
                <div>
                    <input class="inputAdd" style="width: 180px" required  type="text" placeholder=" Название проекта" name="title"/>
                </div>
                <div>
                    <label for="org">Организация</label>
                    <select name="org" id="org">
                        <option selected value="ООО 'ЗНАК-63'">ООО "ЗНАК-63"</option>
                        <option value='ООО "НПЦ" "ИТС"'>ООО "НПЦ" "ИТС"</option>
                    </select>
                </div>
                <div class="divLvl">
                    <span>Архивный проект:</span><input style="margin-bottom: 10px" id="archival" type="checkbox" name="archival">
                </div>
                <div class="divSubmit">
                    <input class="submitAdd" type="submit" value="Добавить">
                </div>
            </form>
        </div>
    </div>
</div>

<div id="categoryModal" class="modalDialog">
    <div class="addCatDiv">
        <a href="#close" title="Закрыть" class="close">X</a>
        <div><h3>Добавление категории</h3></div>
        <form id="addtaskform" action="/addTask" method="post">
            <div>
                <select id="projsel" name="projsel" required>
                    <option value="0" selected disabled>Выберите проект</option>
                    <c:forEach  items="${descs}" var="desc">
                        <option value="${desc.key}">${desc.value}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="text" required id="uplvl" name="upcategory"><label for="uplvl">Родительская категория</label>
                <input type="text" id="downlvl" name="downcategory"><label for="downlvl">Дочерняя категория</label>
            </div>
            <div>
                <input class="submitAdd" type="submit" value="Добавить">
            </div>
        </form>
    </div>
</div>
</body>
</html>
