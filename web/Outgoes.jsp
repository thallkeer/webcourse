<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="app.entities.Employee" %>
<%@ page import="app.dao.impl.EmployeeDAO" %>
<%@ page import="app.dao.IEmployeeDAO" %>
<%@ page import="app.dao.BaseDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<%
    String login = (String) session.getAttribute("login");
    Integer emp_id=0;
    if (request.getParameter("emp_id")!=null){
        emp_id = Integer.valueOf(request.getParameter("emp_id"));
    }
%>

<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/popupstyle.css"/>

</head>
<body>
<table align="center" border="1">
    <caption>Расходы сотрудника <%=login%></caption>
    <thead>
    <td>Статья расходов</td>
    <td>Сумма</td>
    </thead>
    <tbody>
    <tr>
        <td>Здесь что-то будет</td>
        <td>Здесь тоже что-то будет</td>
    </tr>
    </tbody>
</table>

<div>
<a href="AddOutgo.jsp?emp_id=<%=emp_id%>">Добавить расход</a>
</div>

</body>
</html>
