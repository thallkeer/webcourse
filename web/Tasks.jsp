<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String login = (String) session.getAttribute("login");
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    TaskDAO taskDAO = new TaskDAO(dao);
    List<Task> tasks = null;//taskDAO.getUserTasks(login);
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
    <a href="AddTask.jsp?login=<%=login%>">Добавить проект</a>
</div>
</div>
</body>
</html>
