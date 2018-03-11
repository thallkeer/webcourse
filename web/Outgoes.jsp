<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<%
    String login = (String) request.getAttribute("login");
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    ITaskDAO taskDAO = new TaskDAO(dao);
//    session.setAttribute("dao", dao);
    List<String> descs = taskDAO.getParents();


%>

<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link href="resources/Output.json"/>
    <script type="text/javascript" src="resources/dynamicselect.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<script type="text/javascript" src="resources/Output.json"></script>


<form  action="/ChangeOptions" method="post">

<select id="first" name="firstchoice" onload="this.selectedIndex=0" onchange="localStorage.index=this.selectedIndex; if (this.selectedIndex) this.form.submit()">
    <option selected disabled>Выберите расход</option>
    <%
        for (int i=0;i<descs.size();i++) {
    %>
    <option value="<%=i%>"><%=descs.get(i)%></option>
    <%}%>
</select>

    <%--<select id="first" name="firstchoice"  onchange="setSecond()">--%>
        <%--<option selected value="-1" disabled>Выберите расход</option>--%>
        <%--<option value="Проект">Проект</option>--%>
        <%--<option value="Офис">Офис</option>--%>
        <%--<option value="Производство">Производство</option>--%>
    <%--</select>--%>

    <%--localStorage.secondindex=this.selectedIndex;--%>
    <script>
        if(localStorage.secondindex!==undefined ) {
            document.getElementById("first").selectedIndex = localStorage.index;
            localStorage.clear(); }
    </script>

<select id="second" name="secondchoice">
        <option selected value="-1" disabled>Выбрать</option>
        <option>Выберите расход первого уровня</option>
         <%--<%--%>
             <%--list = (List<String>)request.getAttribute("descs2");--%>
             <%--if (list!=null){--%>
                 <%--for (int i=0;i<list.size();i++) {--%>
         <%--%>--%>
            <%--<option value="<%=i%>"><%=list.get(i)%></option>--%>
         <%--<%}}%>--%>
</select>
    <%--<select id="third"  name="thirdchoice">--%>
        <%--<option selected disabled>Выбрать</option>--%>
        <%--<%--%>
            <%--listthird = (List<String>)request.getAttribute("descs3");--%>
            <%--if (listthird!=null){--%>
                <%--for (int i=0;i<listthird.size();i++) {--%>
        <%--%>--%>
        <%--<option value="<%=i%>"><%=listthird.get(i)%></option>--%>
        <%--<%}}%>--%>
    <%--</select>--%>
</form>

<table align="center" border="1">
    <caption>Расходы сотрудника <%=login%></caption>
</table>
</body>
</html>
