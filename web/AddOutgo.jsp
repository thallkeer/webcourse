<%@ page import="java.util.Map" %>
<%@ page import="app.dao.impl.TaskDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dao.ITaskDAO" %>
<%@ page import="app.dao.impl.PostgresDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String login = (String) request.getAttribute("login");
    Integer emp_id=0;
    if (request.getParameter("emp_id")!=null){
        emp_id = Integer.valueOf(request.getParameter("emp_id"));
    }
    ITaskDAO taskDAO = new TaskDAO((PostgresDAO) session.getAttribute("dao"));
    List<String> descs = taskDAO.getParents();
%>
<head>
    <title>Добавление расхода</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/popupstyle.css"/>
</head>
<body>
<%--<div id="hidden" style="display: none;">--%>
    <%--<div class="overlay"></div>--%>
    <%--<div class="visible">--%>
        <form id="addform" action="AddOutgo?emp_id=<%=emp_id%>" method="post">
            <table align="center">
            <%--<div class="content">--%>
                <%--<div>--%>
                <tr>
                    <td><select id="first" name="firstchoice" onchange="this.form.submit()">
                        <option value="0" selected disabled>Выберите расход</option>
                        <%
                            for (int i=0;i<descs.size();i++) {
                        %>
                        <option value="<%=i+1%>"
                                <%
                                    if (request.getParameter("firstchoice")!=null){
                                        if (Integer.valueOf(request.getParameter("firstchoice"))==i+1){
                                            out.print("selected");
                                        }
                                    }
                                %>
                        ><%=descs.get(i)%></option>
                        <%}%>
                    </select></td>
                </tr>
                <%--</div>--%>
                <%--<div>--%>
                <tr>
                    <td><select  id="second" name="secondchoice" onchange="this.form.submit()">
                        <option selected value="0" disabled>Выбрать</option>
                        <%--<option>Выберите расход первого уровня</option>--%>
                        <%
                            if (request.getParameter("firstchoice")!=null){
                                Map<Integer,String> secondvals = taskDAO.getNextLvl(Integer.valueOf(request.getParameter("firstchoice")));
                                for (Map.Entry entry : secondvals.entrySet()){
                        %>
                        <option value="<%=entry.getKey()%>"
                                <%
                                    if (request.getParameter("secondchoice")!=null)
                                        if (Integer.valueOf(request.getParameter("secondchoice"))==entry.getKey()){
                                            out.print("selected");
                                        }
                                %>
                        ><%=entry.getValue()%></option>
                        <%}}%>
                    </select></td>
                </tr>
                <%--</div>--%>
                <%--<div>--%>
                <tr>
                    <td><select id="third"  name="thirdchoice">
                        <option selected value="0" disabled>Выбрать</option>
                        <%
                            if (request.getParameter("firstchoice")!=null && request.getParameter("secondchoice")!=null){
                                Map<Integer,String> thirdvals = taskDAO.getNextLvl(Integer.valueOf(request.getParameter("secondchoice")));
                                for (Map.Entry entry : thirdvals.entrySet()){
                        %>
                        <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                        <%}}%>
                    </select></td>
                </tr>
                <%--</div>--%>
            <%--</div>--%>
                <tr>
                <td><input type="submit"></td>
                </tr>
            </table>
        </form>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>
