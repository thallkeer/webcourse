<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.impl.OutgoDAO" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    PostgresDAO dao = new PostgresDAO();
    dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
    dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    TaskDAO taskDAO = new TaskDAO(dao);
    List<String> parents = taskDAO.getParents();
    OutgoDAO outgoDAO = new OutgoDAO(dao);
    Map<Integer,Integer> outs = outgoDAO.getPtaskSum(3,1);

%>
<head>
    <title>Document</title>
    <link rel="stylesheet" href="resources/teststyle.css">
</head>
<body>
<ul id="trees">
    <li>
        1
        <ul class="box_hide">
            <%--<% for (String desc :--%>
                    <%--parents) {--%>
            <%--%>--%>
            <li>Проект
                <ul class="box_hide">
                    <% for (Map.Entry entry :
                            outs.entrySet()) {
                    %>
                    <li><%=entry.getValue()%></li>
                    <%}%>
                </ul>
            </li>
            <%--<li>1.2</li>--%>
            <%--<li>1.3</li>--%>
            <%--<li>--%>
                <%--1.4--%>
                <%--<ul class="box_hide">--%>
                    <%--<li>1.4.1</li>--%>
                    <%--<li>1.4.2</li>--%>
                    <%--<li>1.4.3</li>--%>
                    <%--<li>1.4.4</li>--%>
                    <%--<li>1.4.5</li>--%>
                    <%--<li>1.4.6</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<% }%>--%>
        </ul>
    </li>
    <li>2</li>
    <li>3</li>
    <li>4</li>
    <li>
        5
        <ul class="box_hide">
            <li>5.1</li>
            <li>5.2</li>
            <li>5.3</li>
            <li>5.4</li>
        </ul>
    </li>
</ul>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $("#trees li").click(function(event){
            $(this).children("ul").toggleClass("box_hide");
            event.stopPropagation();
        });
    });
</script>
</body>
</html>
