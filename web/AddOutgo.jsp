<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление расхода</title>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <link rel="stylesheet" type="text/css" href="resources/popupstyle.css"/>
</head>
<body>
<%--<div id="hidden" style="display: none;">--%>
    <%--<div class="overlay"></div>--%>
    <%--<div class="visible">--%>

<%--<div class="content">--%>
<%--<div>--%>

        <form id="addform" action="/changeOptions" method="post">
            <table align="center">
                <tr>
                    <td><select id="first" name="firstchoice" required onchange="this.form.submit()">
                        <option value="0" selected disabled>Выберите расход</option>

                        <c:forEach  items="${descs}" var="desc">
                            <option value="${desc.key}"
                                ${desc.key == indexes[0] ? 'selected' : ''}
                            >${desc.value}
                            </option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <%--</div>--%>
                <%--<div>--%>
                <tr>
                    <td><select  id="second" name="secondchoice" required onchange="this.form.submit()">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${seconds}"  var="sec">
                            <option value="${sec.key}"
                                ${sec.key == indexes[1] ? 'selected' : ''}
                            >${sec.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <%--</div>--%>
                <%--<div>--%>
                <tr>
                    <td><select id="third" name="thirdchoice">
                        <option selected value="0" disabled>Выбрать</option>
                        <c:forEach items="${thirds}" var="third">
                            <option value="${third.key}"${desc.key == indexes[2] ? 'selected' : ''}>${third.value}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <%--</div>--%>
            <%--</div>--%>
                <tr>
                    <td><input name="sum" type="number" value="0" placeholder="1.0" step="0.01" min="0"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Добавить" formaction="/addOutgo" formmethod="post"></td>
                <td><a href="/addOutgo?emp_id=${emp_id}">Добавить</a></td>
                </tr>
            </table>
        </form>


    <%--</div>--%>
<%--</div>--%>
</body>
</html>
