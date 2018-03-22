<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Редактирование пользователя</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/editUser?emp_id=${emp.employee_id}" method="post">
    <table align="center">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="login" value="${emp.login}"/></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="${emp.password}"/></td>
        </tr>
        <tr>
            <td>ФИО</td>
            <td><input type="text" name="fio" value="${emp.fio}"/></td>
        </tr>
        <tr>
            <td>Уровень доступа</td>
            <td><input type="number" min="1" max="4" name="auth_lvl" value="${emp.auth_lvl}"/></td>
        </tr>
        <tr>
            <td>Баланс</td>
            <td><input type="number"  name="account" value="${emp.account}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>

</body>
</html>
