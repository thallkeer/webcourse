<%@ page import="app.entities.Task" %>
<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.impl.EmployeeDAO" %><%--
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
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>

<%
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    ITaskDAO taskDAO = new app.dao.impl.TaskDAO(dao);
%>

<body>

<button id="add-outgo">Добавить расход</button>

<sql:setDataSource  var="co"   driver="org.postgresql.Driver"
                    url="jdbc:postgresql://localhost:5432/finance_flows"

                    user="postgres"
                    password="1234"
/>

<sql:query var="description" dataSource="${co}">
    SELECT description from task where task_id = 1
</sql:query>

<div id="dialog-form" title="Добавить расход">
    <form action="<%=request.getContextPath()%>/AddOutgoServlet" method="post">
        <fieldset>


    <select id="first-choice">
        <%
            List<String> descrs = taskDAO.getTaskDescriptionByLvl(2);
            int size = descrs.size();
        %>
        <% for (int i = 0;i<size;i++){%>
        <option value=<%=descrs.get(i)%>><%=descrs.get(i)%></option>
        <%}%>
        <%--<c:forEach var="desc" items="${descrs.rowsByIndex}">--%>
            <%--<option>--%>
                <%--<c:forEach var="item" items="${desc}">--%>
                    <%--<c:out value="${item}"/>--%>
                <%--</c:forEach>--%>
            <%--</option>--%>
        <%--</c:forEach>--%>
    </select>
    <br>
    <select id="second-choice">
        <option>Please choose from above</option>
    </select>
            <br>
    <input type="submit" > <!--tabindex="-1" style="position:absolute; top:-1000px"-->
        </fieldset>
    </form>
        <%--<fieldset>--%>
            <%--<label for="name">Name</label>--%>
            <%--<input type="text" name="name" id="name" value="Jane Smith" class="text ui-widget-content ui-corner-all">--%>
            <%--<label for="email">Email</label>--%>
            <%--<input type="text" name="email" id="email" value="jane@smith.com" class="text ui-widget-content ui-corner-all">--%>
            <%--<label for="password">Password</label>--%>
            <%--<input type="password" name="password" id="password" value="xxxxxxx" class="text ui-widget-content ui-corner-all">--%>

            <%--<!-- Allow form submission with keyboard without duplicating the dialog button -->--%>
            <%--<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">--%>
        <%--</fieldset>--%>

</div>



<sql:query var="outgoes" dataSource="${co}">
    SELECT t.description as "Статья расходов",summ as "Потрачено" FROM outgo o left join (select employee_id,login,fio from employee) as e on e.employee_id=o.emp_id
    left join (select task_id,description from task) as t on t.task_id=o.task_id
 </sql:query>


<table align="center" border="1">
    <%
        String login = (String) request.getAttribute("login");
        List<Task> tasks = taskDAO.getUserTasks("login");
    %>

    <caption>Расходы сотрудника <%=request.getAttribute("login")%></caption>
       <tr>
        <c:forEach var="columnName" items="${outgoes.columnNames}">
            <th><c:out value="${columnName}"/></th>
        </c:forEach>
    </tr>
    <!-- column data -->
    <c:forEach var="row" items="${outgoes.rowsByIndex}">
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
