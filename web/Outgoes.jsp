<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>


<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>
<body>
<table align="center" border="1">
    <caption>Расходы сотрудника <c:out value="${param.fio}" /></caption>
    <thead>
    <th>Идентификатор</th>
    <th>Идентификатор траты</th>
    <th>Описание</th>
    <th>Сумма</th>
    <th>Редактировать</th>
    <th>Удалить</th>
    </thead>
    <c:forEach items="${outgos}" var="outgo">

    <tbody>
    <tr>
        <td><c:out value="${outgo.outgo_id}"/>
        </td>
        <td><c:out value="${outgo.task_id}"/>
        </td>
        <td><c:out value="${outgo.task_description}"/>
        </td>
        <td><c:out value="${outgo.summ}"/>
        </td>
        <td><a href="/editOutgo?outgo_id=${outgo.outgo_id}">Редактировать</a></td>
        <td><a href="/deleteOutgo?outgo_id=${outgo.outgo_id}" class="pri">Удалить</a></td>
    </tr>
    </tbody>
    </c:forEach>
    <%--<tr>--%>
        <%--<td>--%>
            <%--<a href="/addOutgo?emp_id=${param.emp_id}" class="btn btn-add"--%>
            <%--id="btnAdd" style="margin-bottom: 10px; white-space: nowrap;">--%>
                <%--<img src="resources/plus-flat.png">--%>
                <%--Добавить--%>
            <%--</a>--%>
        <%--</td>--%>
    <%--</tr>--%>
</table>
<div><a href="/addOutgo?emp_id=${param.emp_id}"
        class="btnAdd">Добавить
</a></div>

</body>
</html>
