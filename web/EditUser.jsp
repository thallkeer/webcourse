<%@ page import="app.entities.Employee" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.IEmployeeDAO" %>
<%@ page import="app.dao.impl.EmployeeDAO" %><%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 06.03.2018
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Integer emp_id = Integer.valueOf(request.getParameter("emp_id"));
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    IEmployeeDAO empDAO = new EmployeeDAO(dao);
    Employee emp = empDAO.getUser(emp_id);
%>
<head>
    <title>Редактирование пользователя</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
<form action="/EditUser" method="post">
    <table align="center">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value="<%=emp.getLogin()%>"/></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="<%=emp.getPassword()%>"/></td>
        </tr>
        <tr>
            <td>ФИО</td>
            <td><input type="text" name="fio" value="<%=emp.getFio()%>"/></td>
        </tr>
        <tr>
            <td>Уровень доступа</td>
            <td><input type="number" min="1" max="4" name="auth_lvl" value="<%=emp.getAuth_lvl()%>"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>

</body>
</html>
