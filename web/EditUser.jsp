<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Редактирование пользователя</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
<div class="parent">
    <form action="/editUser?emp_id=${emp.employee_id}" method="post">
        <div>
            <input class="inputAdd" required  type="text"  value="${emp.login}" name="login"/>
        </div>
        <div>
            <input class="inputAdd" required type="text" value="${emp.password}" name="password"/>
        </div>
        <div >
            <input class="inputAdd"  type="text" value="${emp.fio}" name="fio"/>
        </div>
        <div>
            <p>Уровень доступа: <input class="inputLvl" type="number" id="auth_lvl"value="${emp.auth_lvl}" min="1" max="4" name="auth_lvl" /></p>
        </div>
        <div>
            <input class="inputAdd" type="number"  name="account" value="${emp.account}"/>
        </div>
        <div>
            <input class="submitAdd" type="submit" value="Добавить">
        </div>
    </form>
</div>
</body>
</html>
