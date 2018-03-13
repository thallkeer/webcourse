<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Task" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String login = (String) session.getAttribute("login");
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    TaskDAO taskDAO = new TaskDAO(dao);
    List<Task> tasks = null;//taskDAO.getUserTasks(login);
    Task testtask = taskDAO.getTasksTree(31);
    out.print(testtask.getTask_id());
    out.print(testtask.getPtask_id());
    tasks = taskDAO.getAll();
%>
<head>
    <title>Задачи</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>
<body>
<div>
<table align="center" border="1">
    <caption>Проекты сотрудника<%=login%></caption>
    <thead>
    <td>Номер проекта</td>
    <td>Номер верхней категории</td>
    <td>Проект</td>
    <td>Редактировать</td>
    <td>Удалить</td>
    </thead>
    <% for (Task task :
            tasks) {
    %>
    <tbody>
    <tr>
        <td><%=task.getTask_id()%></td>
        <td><%=task.getPtask_id()%></td>
        <td><%=task.getDescription()%></td>
    </tr>
    </tbody>
    <% }%>
</table>

<div class="description">
    <a>Добавить проект</a>
</div>
</div>
</body>
</html>
