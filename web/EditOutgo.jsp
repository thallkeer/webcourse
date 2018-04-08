<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kir73
  Date: 26.03.2018
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/fortest.css"/>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
        }

        .formEditUser {
            max-width: 230px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .formEditUser input[type="text"],
        .formEditUser input[type="text"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>
</head>
<body>
<div>
    <form class="formEditUser" id="addform" name="addform" action="/changeOptions?editMode" method="post">
        <div><h3>Редактирование расхода</h3></div>
        <div><select id="first" name="firstchoice" required onchange="this.form.submit()">
            <option value="0" selected disabled>Выберите расход</option>
            <c:forEach  items="${descs}" var="desc">
                <option value="${desc.key}"
                    ${desc.key == indexes[0] ? 'selected' : ''}
                >${desc.value}
                </option>
            </c:forEach>
        </select></div>

             <div><select  id="second" name="secondchoice" required onchange="this.form.submit()">
                 <option selected value="0" disabled>Выбрать</option>
                 <c:forEach items="${seconds}"  var="sec">
                     <option value="${sec.key}"
                         ${sec.key == indexes[1] ? 'selected' : ''}
                     >${sec.value}</option>
                 </c:forEach>
             </select></div>
        <div> <select id="third" name="thirdchoice">
                <option selected value="0" disabled>Выбрать</option>
                <c:forEach items="${thirds}" var="third">
                    <option value="${third.key}"${third.key == indexes[2] ? 'selected' : ''}>${third.value}</option>
                </c:forEach>
            </select>
        </div>

        <div><input class="inputAcc"  name="sum" type="number" value="${sum}" size="5" step="0.01" min="0"></div>
        <div><input class="submitAdd" type="submit" formaction="/editOutgo" formmethod="post"></div>
    </form>
</div>
</body>
</html>
