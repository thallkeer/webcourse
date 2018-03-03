<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="teststyle.css">
</head>
<body>
<header class="header">
    <nav class="navbar navbar-static-top top-nav" role="navigation" style="margin-bottom: 0">
        <ul class="nav navbar-nav">
            <li class="nav-header logo">
                <a href="/app/onboarding">
                    <span class="logo-wrap">План<strong class="logo-bold">Факт</strong></span>
                </a>
            </li>
        </ul>
            <div class="login-r"><form action="/app/account/log-off" class="navbar-right" id="logoutForm" method="post">        <ul class="nav navbar-top-links account-nav">
                <li>
                    <div class="dropdown dropdown-profile">
                        <a data-toggle="dropdown" class="dropdown-toggle profile-name" href="#" aria-expanded="false">
                        <span class="clear">
                            <span class="block">
                                <i class="fa fa-cog login-cog"></i>
                                <strong class="font-bold account-name"><%=request.getAttribute("login") %></strong> <b class="caret"></b>
                            </span>
                        </span>
                        </a>

                        <ul class="dropdown-menu fadeIn m-t-xs page-menu">

                            <li><a href="/app/projects">Проекты</a></li>
                            <li><a href="/app/contractors">Контрагенты</a></li>
                            <li><a href="/app/transaction-categories">Статьи доходов и расходов</a></li>
                            <li><a href="/app/accounts">Мои счета</a></li>
                            <li class="dropdown-divider"></li>
                            <li><a href="/app/finance/pay">Мои оплаты</a></li>
                            <li class="dropdown-divider"></li>
                            <li><a href="/app/manage/general-settings">Настройки</a></li>
                            <li class="dropdown-divider"></li>
                            <li><a class="removeCookie" href="javascript:document.getElementById('logoutForm').submit()">Выйти</a></li>
                        </ul>

                    </div>
                </li>
                <li class="logout">
                    <a href="javascript:document.getElementById('logoutForm').submit()">
                        <i class="fa fa-custom fa-custom-logout"></i>
                    </a>
                </li>
            </ul>
            </form></div>
    </nav>

</header>

Welcome <%=request.getAttribute("login") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>
