<%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 06.03.2018
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление пользователя</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
    <form action="/addUser" method="post">
        <table align="center">
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                <td>FIO</td>
                <td><input type="text" name="fio" /></td>
            </tr>
            <tr>
                <td>Auth_lvl</td>
                <td><input type="number" min="1" max="4" name="auth_lvl" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit"></td>
            </tr>
        </table>
    </form>
</body>
</html>
