<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Page</title>
</head>
<body>
<center><h2>User's Home</h2></center>
Welcome <%=request.getAttribute("login") %>

<table border="1">
    <thead>
    <tr>
        <th>Расходы сотрудника</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>To view the contact details of an IFPWAFCAD certified former
            professional wrestler in your area, select a subject below:</td>
    </tr>
    <tr>
        <td>
                <li><a href="/Views/Outgoes">Расходы</a></li>
        </td>
    </tr>
    </tbody>
</table>



<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>