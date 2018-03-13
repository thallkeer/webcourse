<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
</head>
<body>
<ul>
    <li><a href="Outgoes.jsp">Расходы</a></li>
    <li><a href="Tasks.jsp">Проекты</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#about">About</a></li>
    <li id="li06"><div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div></li>
    <li id="li05">Welcome <%=session.getAttribute("login") %></li>
    <li id="li04"><h2>User's Home</h2></li>
</ul>
</body>
</html>