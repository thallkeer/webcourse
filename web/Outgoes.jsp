<%@ page import="app.entities.Task" %>
<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.impl.EmployeeDAO" %>
<%@ page import="app.entities.Employee" %>
<%@ page import="app.dao.IEmployeeDAO" %>
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
%>

<head>
    <title>Расходы</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <script type="text/javascript" src="resources/linkedselect.js"></script>
</head>




<form  action="/ChangeOptions" method="post">

<select id="first" name="firstchoice" onload="this.selectedIndex=0" onchange="localStorage.firstindex=this.selectedIndex; if (this.selectedIndex) this.form.submit()">
    <option selected disabled>Выберите расход</option>
    <% List<String> descs = taskDAO.getParents();
        for (int i=0;i<descs.size();i++) {
    %>
    <option value="<%=i%>"><%=descs.get(i)%></option>
    <%}%>
    <input name="hiden" id="hiden" type="hidden" value="false">
   </select>
    <script>
        //восстанавливаем запомненное значение, если есть
            if(localStorage.firstindex!==undefined ) {
                document.getElementById("first").selectedIndex = localStorage.firstindex;
                localStorage.clear()
                document.getElementById("hiden").value = "true";
            }
    </script>

<select id="second" name="secondchoice" onload="this.selectedIndex=0" onchange="localStorage.secondindex=this.selectedIndex; if (this.selectedIndex) this.form.submit()">
        <option disabled>Выбрать</option>
         <%
             List<String> list = (List<String>)request.getAttribute("descs2");
             if (list!=null){
                 for (int i=0;i<list.size();i++) {
         %>
            <option value="<%=i%>"><%=list.get(i)%></option>
         <%}}%>
</select>
    <script>
        // if(localStorage.secondindex!==undefined ) {
        //     document.getElementById("second").selectedIndex = localStorage.secondindex;
        //     localStorage.clear() }
    </script>

    <select id="third"  name="thirdchoice">
        <option disabled>Выбрать</option>
        <%
            List<String> listthird = (List<String>)request.getAttribute("descs3");
            if (listthird!=null){
                for (int i=0;i<listthird.size();i++) {
        %>
        <option value="<%=i%>"><%=listthird.get(i)%></option>
        <%}}%>
    </select>
</form>

<script>
    var syncList1 = new syncList;

    syncList().dataList={
        'Проект':{
            'win':'Windows',
            'mac':'Mac'
        },

        'Офис':{
            'safari_mac':'Macс'
        }
    };
    syncList1.sync("first","second","third");

</script>

<table align="center" border="1">
    <caption>Расходы сотрудника <%=login%></caption>
</table>
</body>
</html>
