<%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 03.03.2018
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>Расходы</title>
</head>
<body>

<%--<div class="cont">--%>
    <%--<form name="form" action="<%=request.getContextPath()%>--%>
<%--/AddOutgoServlet" method="post">--%>

    <%--</form>--%>
<%--</div>--%>
<%--<li><a href="/Views/Outgoes">Расходы</a></li>--%>
<sql:setDataSource  var="co"   driver="org.postgresql.Driver"
                    url="jdbc:postgresql://localhost:5432/finance_flows"

                    user="postgres"
                    password=""
/>

<sql:query var="employees" dataSource="${co}">
    SELECT employee_id,login,password,fio FROM employee
</sql:query>

<table border="1">
    <!-- column headers -->
    <tr>
        <c:forEach var="columnName" items="${employees.columnNames}">
            <th><c:out value="${columnName}"/></th>
        </c:forEach>
    </tr>
    <!-- column data -->
    <c:forEach var="row" items="${employees.rowsByIndex}">
        <tr>
            <c:forEach var="column" items="${row}">
                <td><c:out value="${column}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>


<%--<table align="center" border="0">--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th>Расходы сотрудника</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<tr>--%>
        <%--<td></td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
<%--</table>--%>
</body>
</html>
