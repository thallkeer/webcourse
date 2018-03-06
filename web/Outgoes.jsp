<%@ page import="app.entities.Task" %>
<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.impl.EmployeeDAO" %>
<%@ page import="app.entities.Employee" %>
<%@ page import="app.dao.IEmployeeDAO" %><%--
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
<%
    String login = (String) request.getAttribute("login");
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
%>
<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <script type="text/javascript" src="resources/linkedselect.js"></script>
</head>

<body>
    <form action="<%=request.getContextPath()%>/AddOutgoServlet" method="post">
        <fieldset>
            <!-- Первый (главный) список (изначально заполнен вручную) -->
            <select id="List1" onchange="getSelectedValue()">
                <%
                    List<String> descrs = taskDAO.getParents();
                    int size = descrs.size()-1;
                %>
                <% for (int i = size;i>=0;i--){%>
                <option value=<%=descrs.get(i)%>><%=descrs.get(i)%></option>
                <%}%>
            </select>
            <script>

                function getSelectedValue() {
                    var selectedValue = document.getElementById("List1").value;

                }
            </script>

            <!-- Подчиненный список 1 (изначально пуст) -->
            <select id="List2"></select>

            <!-- Подчиненный список 2 (изначально пуст) -->
            <select id="List3"></select>

            <script type="text/javascript">
                // Создаем новый объект связанных списков
                var syncList1 = new syncList;

                // Определяем значения подчиненных списков (2 и 3 селектов)
                syncList1.dataList = {

                    /* Определяем элементы второго списка в зависимости
                    от выбранного значения в первом списке */
                    'Проект':{
                        'ie_win':'Windows',
                        'ie_mac':'Mac',

                    },

                    'safari':{
                        'safari_mac':'Mac'
                    },

                    /* Определяем элементы третьего списка в зависимости
                    от выбранного значения во втором списке */

                    'ie_win':{
                        'ie_win_5':'версия 5',
                        'ie_win_6':'версия 6'
                    },

                    'ie_mac':{
                        'ie_mac_5':'версия 5'
                    },

                    'safari_mac':{
                        'safari_mac_1':'версия 1',
                        'safari_mac_2':'версия 2'
                    }
                };

                // Включаем синхронизацию связанных списков
                syncList1.sync("List1","List2","List3");
            </script>
    <%--<select id="first-choice">--%>
        <%--<%--%>
            <%--List<String> descrs = taskDAO.getParents();--%>
            <%--int size = descrs.size()-1;--%>
        <%--%>--%>
        <%--<% for (int i = size;i>=0;i--){%>--%>
        <%--<option value=<%=descrs.get(i)%>><%=descrs.get(i)%></option>--%>
        <%--<%}%>--%>
    <%--</select>--%>
    <%--<br>--%>
    <%--<select id="second-choice">--%>
        <%--var objSel = document.myForm.mySelect;--%>
    <%--</select>--%>
            <%--<br>--%>
    <%--<input type="submit" >--%>
        </fieldset>
    </form>

<table align="center" border="1">
    <caption>Расходы сотрудника <%=login%></caption>
    <thead>
    <td>Расход</td>
    <td>Сумма</td>
    </thead>
    <% for (String desc :
            ) {
    %>
    <tbody>
    <tr>
        <td><%= emp.getEmployee_id() %>
        </td>
        <td><%= emp.getLogin() %>
        </td>
        <td><%= emp.getPassword() %>
        </td>
        <td><%= emp.getFio() %>
        </td>
        <td><%= emp.getAuth_lvl() %>
        </td>
        <td>Тут что-то будет</td>
        <td><a href="EditUser.jsp?emp_id=<%=emp.getEmployee_id()%>">Редактировать</a></td>
        <td><a href="/DeleteUser?emp_id=<%=emp.getEmployee_id()%>">Удалить</a></td>
    </tr>
    </tbody>
    <% }%>
</table>
</body>
</html>
