<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>


<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/popupstyle.css"/>

</head>
<body>
<table align="center" border="1">
    <caption>Расходы сотрудника <c:out value="${param.fio}" /></caption>
    <thead>
    <td>Идентификатор</td>
    <td>Идентификатор траты</td>
    <td>Описание</td>
    <td>Сумма</td>
    <td>Редактировать</td>
    <td>Удалить</td>
    </thead>

    <c:forEach items="${outgos}" var="outgo">
    <tbody>
    <tr>
        <td><c:out value="${outgo.outgo_id}"/>
        </td>
        <td><c:out value="${outgo.task_id.task_id}"/>
        </td>
        <td><c:out value="${outgo.task_id.description}"/>
        </td>
        <td><c:out value="${outgo.summ}"/>
        </td>
        <td>
        <td><a href="/editOutgo?outgo_id=${outgo.outgo_id}">Редактировать</a></td>
        <td><a href="/deleteOutgo?outgo_id=${outgo.outgo_id}">Удалить</a></td>
        </td>
    </tr>
    </tbody>
    </c:forEach>
    <tr>
        <td><a href="/addOutgo?emp_id=${param.emp_id}">Добавить расход</a></td>
    </tr>
</table>
</body>
</html>
