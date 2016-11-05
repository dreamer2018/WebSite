<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/5/16
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.xiyoulinux.model.Events" %>
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
                        <li><a href="/admin/eventsedit"><i class="icon-font">&#xe034;</i>文章管理</a></li>
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
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.jsp">首页</a><span
                    class="crumb-step">&gt;</span><span class="crumb-name">活动管理</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <div class="result-list">
                    <a href="/admin/eventsedit.jsp"><i class="icon-font"></i>添加</a>
                </div>
            </div>
            <div class="result-content" id="fid">
                <table class="result-tab" width="100%" id="tableid" cellpadding="0" cellspacing="0">
                    <tr>
                        <th class="tc">标题</th>
                        <th class="tc">日期</th>
                        <th class="tc">时间</th>
                        <th class="tc">地点</th>
                        <th class="tc">预览</th>
                        <th class="tc">删除</th>
                    </tr>
                    <%
                        ArrayList<Events> eventsList = (ArrayList<Events>) request.getAttribute("eventsList");
                        for (int i = 0; i < eventsList.size(); i++) {
                    %>
                        <tr>
                            <td class="tc">
                                <a href="/admin/eventsedit?id=<%=eventsList.get(i).getId()%>"><%=eventsList.get(i).getTitle()%></a>
                            </td>
                            <td class="tc"><%=eventsList.get(i).getDate()%></td>
                            <td class="tc"><%=eventsList.get(i).getTime()%></td>
                            <td class="tc"><%=eventsList.get(i).getAddress()%></td>
                            <td class="tc">
                                <a href="/admin/preview?id=<%=eventsList.get(i).getId()%>"><input type="button" class="btn btn-success btn2" value="预览"></a>
                            </td>
                            <td class="tc">
                                <a href="/admin/delete?id=<%=eventsList.get(i).getId()%>"><input type="button" class="btn btn-warning btn2" value="删除"></a>
                            </td>
                        <tr>
                        <%}%>
                </table>
                <div class="list-page" style="margin-left: 85%"> 共<%=eventsList.size()%>条</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
