<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Задачи</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>
<body>
<div>
<table align="center" border="1">
    <caption>Проекты сотрудника</caption>
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
        <td><a href="/editTask?task_id=${task.task_id}">Редактировать</a></td>
        <td><a href="/deleteTask?task_id=${task.task_id}">Удалить</a></td>
    </tr>
    </tbody>
    </c:forEach>
</table>
</div>
<div>
    <a href="AddTask.jsp"  class="btnAdd">Добавить проект</a>
    <a href="/addTask" class="btnAdd">Добавить статью расходов</a>
</div>
</body>
</html>
