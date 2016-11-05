<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/5/16
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>西邮Linux兴趣小组官网-后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<!--网页头部-->
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <ul class="navbar-list clearfix">
                <li style="font-size:20px; font-weight:bold;">西邮Linux兴趣小组官网</li>
                <li style="font-size: 16px;font-style: italic">-后台管理系统</li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#"><i class="icon-font">&#xe014;</i></a></li>
                <li><a href="#"><i class="icon-font">&#xe059;</i></a></li>
            </ul>
        </div>
    </div>
</div>
<!--头部结束-->


<div class="container clearfix">

    <!--网页菜单栏-->
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>文章管理</a>
                    <ul class="sub-menu">
                        <li><a href="/admin/events"><i class="icon-font">&#xe063;</i>活动管理</a></li>
                        <li><a href="/admin/introduction"><i class="icon-font">&#xe034;</i>文章管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>显示管理</a>
                    <ul class="sub-menu">
                        <li><a href="#"><i class="icon-font">&#xe017;</i>首页显示</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--菜单栏结束-->

    <div class="main-wrap">
        <div class="result-wrap">
            <div class="result-title">
                <h1>欢迎使用西邮Linux兴趣小组官网-后台管理</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <p>目前共有文章100篇</p><br/>
                    <p>活动30个</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>