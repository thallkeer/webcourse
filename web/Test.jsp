<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Document</title>
    <link rel="stylesheet" href="resources/teststyle.css">
</head>

<body>

<ul id="trees">
    <%--<li>--%>
        <%--Проект--%>
        <%--<ul class="box_hide">--%>
            <%--<li>Аренда техники</li>--%>
            <%--<li>Транспортные услуги</li>--%>
        <%--<li>--%>
        <%--ГСМ--%>
            <%--<ul class="box_hide">--%>
                <%--<li>1.4.1</li>--%>
                <%--<li>1.4.2</li>--%>
                <%--<li>1.4.3</li>--%>
                <%--<li>1.4.4</li>--%>
                <%--<li>1.4.5</li>--%>
                <%--<li>1.4.6</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--</ul>--%>
    <%--</li>--%>
        <%--<li>2</li>--%>
    <%--<li>3</li>--%>
    <%--<li>4</li>--%>
    <%--<li>--%>
        <%--5--%>
        <%--<ul class="box_hide">--%>
            <%--<li>5.1</li>--%>
            <%--<li>5.2</li>--%>
            <%--<li>5.3</li>--%>
            <%--<li>5.4</li>--%>
        <%--</ul>--%>
    <%--</li>--%>
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
