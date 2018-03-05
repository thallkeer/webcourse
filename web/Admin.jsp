<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="../Styles/teststyle.css">
</head>
<body>
Welcome <%=request.getAttribute("login") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>
