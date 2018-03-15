<%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 14.03.2018
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление проекта</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
<form action="AddTask" method="post">
    <table align="center">
        <tr>
            <td>Название проекта</td>
            <td><input type="text" name="title" /></td>
        </tr>
        <tr>
            <td>Организация</td>
            <td><select name="org">
                <option value="ООО 'ЗНАК-63'">ООО "ЗНАК-63"</option>
                <option value='ООО "НПЦ" "ИТС"'>ООО "НПЦ" "ИТС"</option>
            </select></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="archival">Архивный</td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
