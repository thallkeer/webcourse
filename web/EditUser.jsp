<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Редактирование пользователя</title>
    <link rel="stylesheet" href="resources/fortest.css">
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
        }

        .formEditUser {
            max-width: 280px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .formEditUser input[type="text"],
        .formEditUser input[type="text"] {
            font-size: 16px;
            height: 25px;
            /*margin-bottom: 15px;*/
            /*padding: 7px 9px;*/
            padding: 5px;
        }
    </style>
</head>
<body>
<div class="parent">
    <form class="formEditUser" action="/editUser?emp_id=${emp.employee_id}" method="post">
        <div><h3>Редактирование пользователя</h3></div>
        <div class="form-group">
            <input id="login" required  type="text"  value="${emp.login}" name="login"/><label for="login">Логин</label>

            <input id="pass" required type="text" value="${emp.password}" name="password"/>
            <label for="pass">Пароль</label>

            <input id="fio"  type="text" value="${emp.fio}" name="fio"/>
            <label for="fio">ФИО</label>
        </div>
        <div>
            <p>Уровень доступа: <input class="inputLvl" type="number" id="auth_lvl"value="${emp.auth_lvl}" min="1" max="4" name="auth_lvl" /></p>
        </div>
        <div>
            <input class="inputAcc" style="margin-bottom: 0px" type="number"  name="account" value="${emp.account}" step="0.01" min="0"/>
            <label style="display: block; margin-bottom: 10px; padding-right: 10px;" for="fio">Баланс</label>
        </div>
        <div>
            <input class="submitAdd" type="submit" value="Принять">
        </div>
    </form>
</div>
</body>
</html>
