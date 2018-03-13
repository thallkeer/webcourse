<%@ page import="app.entities.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.entities.Employee" %>
<%@ page import="app.dao.IEmployeeDAO" %>
<%@ page import="app.dao.impl.EmployeeDAO" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>


<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
Welcome <%=session.getAttribute("login") %>


<%
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    session.setAttribute("dao",dao);
    session.setAttribute("login",request.getAttribute("login"));
    IEmployeeDAO empDAO = new EmployeeDAO(dao);
    List<Employee> emps = null;
    try {
        emps = empDAO.getAll();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<div>
<table align="center" border="1">
    <caption>Cписок сотрудников</caption>
    <thead>
    <td>Табельный номер</td>
    <td>Логин</td>
    <td>Пароль</td>
    <td>ФИО</td>
    <td>Уровень доступа</td>
    <td>Список расходов</td>
    <td>Редактировать</td>
    <td>Удалить</td>
    </thead>
    <% for (Employee emp :
            emps) {
    %>
    <tbody>
    <tr>
        <td><%= emp.getEmployee_id() %>
        </td>
        <td><%= emp.getLogin() %>
        </td>
        <td><%= emp.getPassword() %>
        </td>
        <td><%= emp.getFio() %>
        </td>
        <td><%= emp.getAuth_lvl() %>
        </td>
        <td><a href="Outgoes.jsp?emp_id=<%=emp.getEmployee_id()%>">Расходы</a></td>
        <td><a href="EditUser.jsp?emp_id=<%=emp.getEmployee_id()%>">Редактировать</a></td>
        <td><a href="DeleteUser?emp_id=<%=emp.getEmployee_id()%>">Удалить</a></td>
    </tr>
    </tbody>
    <% }%>
</table>
    <div class="description">
        <a href="AddUser.jsp">Добавить пользователя</a>
    </div>

</div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>

