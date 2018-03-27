<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 26.03.2018
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>
<body>
<table align="center" border="1">
    <caption>Редактирование расхода</caption>
    <form id="addform" name="addform" action="/changeOptions?editMode" method="post">
        <tr><td>
            <select id="first" name="firstchoice" required onchange="this.form.submit()">
                <option value="0" selected disabled>Выберите расход</option>
                <c:forEach  items="${descs}" var="desc">
                    <option value="${desc.key}"
                        ${desc.key == indexes[0] ? 'selected' : ''}
                    >${desc.value}
                    </option>
                </c:forEach>
            </select>
        </td></tr>
        <tr><td>
            <select  id="second" name="secondchoice" required onchange="this.form.submit()">
                <option selected value="0" disabled>Выбрать</option>
                <c:forEach items="${seconds}"  var="sec">
                    <option value="${sec.key}"
                        ${sec.key == indexes[1] ? 'selected' : ''}
                    >${sec.value}</option>
                </c:forEach>
            </select>
             </td></tr>
        <tr><td>
            <select id="third" name="thirdchoice">
                <option selected value="0" disabled>Выбрать</option>
                <c:forEach items="${thirds}" var="third">
                    <option value="${third.key}"${third.key == indexes[2] ? 'selected' : ''}>${third.value}</option>
                </c:forEach>
            </select>
        </td></tr>
        <tr><td><input name="sum" type="number" value="${sum}" size="5" step="0.01" min="0"></td></tr>
        <tr><td><input type="submit" formaction="/editOutgo" formmethod="post"></td></tr>
    </form>

</table>
</body>
</html>
