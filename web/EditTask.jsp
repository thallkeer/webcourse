<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Редактирование категории</title>
    <link rel="stylesheet" href="resources/fortest.css">
</head>
<body>
        <h2 align="center">Редактирование категории</h2>
        <div align="center">
            <form action="/editTask?task_id=${task.task_id}" method="post">
                <div>
                    <label for="downlvl">Наименование</label><br>
                    <input type="text" required id="downlvl" name="description" value="${task.description}">
                </div>
                <div>
                    <label  for="uplvl">Родительская категория</label><br>
                    <input  type="text" disabled id="uplvl" value="${parent}"/>
                </div>
                <div>
                    <input class="submitAdd" type="submit" value="ОК">
                </div>
            </form>
        </div>
</body>
</html>
