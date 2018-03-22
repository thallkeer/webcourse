<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
Welcome <c:out value="${param.login}" />
<div>
<table align="center" border="1">
    <caption>Cписок сотрудников</caption>
    <thead>
    <td>Табельный номер</td>
    <td>Логин</td>
    <td>Пароль</td>
    <td>ФИО</td>
    <td>Уровень доступа</td>
    <td>Баланс</td>
    <td>Список расходов</td>
    <td>Редактировать</td>
    <td>Удалить</td>
    </thead>

    <c:forEach items="${emps}" var="emp">
    <tbody>
    <tr>
        <td><c:out value="${emp.employee_id}"/>
        </td>
        <td><c:out value="${emp.login}"/>
        </td>
        <td><c:out value="${emp.password}"/>
        </td>
        <td><c:out value="${emp.fio}"/>
        </td>
        <td><c:out value="${emp.auth_lvl}"/>
        </td>
        <td><c:out value="${emp.account}"/>
        </td>

        <td><a href="/outgoes?emp_id=${emp.employee_id}">Расходы</a></td>
        <td><a href="/editUser?emp_id=${emp.employee_id}">Редактировать</a></td>
        <td><a href="/deleteUser?emp_id=${emp.employee_id}">Удалить</a></td>

    </tr>
    </tbody>
    </c:forEach>
</table>
    <div class="description">
        <a href="AddUser.jsp">Добавить пользователя</a>
    </div>

</div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>

