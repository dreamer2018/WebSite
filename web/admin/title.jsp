<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/5/16
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.xiyoulinux.model.Title" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="../images/xiyoulinux.png">
    <title>西邮Linux兴趣小组官网-后台管理</title>
    <!-- Bootstrap css -->
    <%--<link href="http://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="http://o.qcloud.com/static_api/v3/assets/fontawesome/css/font-awesome.css" rel="stylesheet">--%>
    <link href="/admin/css/font-awesome.css" rel="stylesheet">
    <!-- 当前项目样式文件 -->
    <link href="/admin/css/sb-admin.css" rel="stylesheet">
    <link href="/admin/css/sb-bk-theme.css" rel="stylesheet">
    <!--蓝鲸平台APP 公用的样式文件 -->
    <%--<link href="http://o.qcloud.com/static_api/v3/bk/css/bk.css?v=1.0.1" rel="stylesheet">--%>
    <link href="/admin/css/bk.css" rel="stylesheet">
    <!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
</head>

<body>

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin">
                <i class="fa fa-leaf f20 mr5"></i>
                后台管理
            </a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
                        class="caret"></b></a>
            </li>
            <li class="dropdown">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> admin
                    <b class="caret"></b></a>
                <ul class="dropdown-menu"><a href="/admin/title"><i class="fa fa-fw fa-desktop"></i>标题管理</a>
                    <%--<li>--%>
                    <%--<a href="javascript:;"><i class="fa fa-fw fa-user"></i> 用户</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="javascript:;"><i class="fa fa-fw fa-envelope"></i> 消息盒</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="javascript:;"><i class="fa fa-fw fa-gear"></i> 设置</a>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <li>
                        <a href="/Logout"><i class="fa fa-fw fa-power-off"></i> 退出</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <%--<li>--%>
                    <%--<a href="/admin/"><i class="fa fa-fw fa-dashboard"></i>首页</a>--%>
                <%--</li>--%>
                <li>
                    <a href="/admin/events"><i class="fa fa-fw fa-table"></i>活动管理</a>
                </li>
                <li>
                    <a href="/admin/blogs"><i class="fa fa-fw fa-edit"></i>文章管理</a>
                </li>
                <li class="active">
                    <a href="/admin/title"><i class="fa fa-fw fa-desktop"></i>标题管理</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row page-header-box">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        标题管理
                    </h1>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    标题列表
                </div>
                <div>
                    <table class="table table-out-bordered table-hover">
                        <tr>
                            <th style="width:50px;">序号</th>
                            <th>标题</th>
                            <th>副标题</th>
                            <th>链接</th>
                            <th>操作</th>
                        </tr>
                        <thead>
                        </thead>
                        <%
                            ArrayList<Title> titleList = (ArrayList<Title>) request.getAttribute("titleList");
                            for (int i = 0; i < titleList.size(); i++) {
                        %>
                        <tbody>
                        <tr>
                            <td><%=i + 1%>
                            </td>
                            <td><%=titleList.get(i).getTitle()%></td>
                            <td><%=titleList.get(i).getSubtitle()%>
                            </td>
                            <td><%=titleList.get(i).getUrl()%></td>
                            <td>
                                <a href="/admin/titleedit?id=<%=titleList.get(i).getId()%>">修改</a>
                            </td>
                        </tr>
                                <%}%>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="12">
                                <div class="pagination-info pull-left">共<%=titleList.size()%>条记录，当前第1/1页，每页20条记录</div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

        </div>

    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
<%--<script src="http://o.qcloud.com/static_api/v3/assets/js/jquery-1.10.2.min.js"></script>--%>
<script src="js/jquery-1.10.2.min.js"></script>
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
<%--<script src="http://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/js/bootstrap.min.js"></script>--%>
<script src="js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<!--<script src="http://o.qcloud.com/static_api/v3/assets/js/html5shiv.min.js"></script>-->
<script src="js/html5shiv.min.js"></script>
<%--<script src="http://o.qcloud.com/static_api/v3/assets/js/respond.min.js"></script>--%>
<script src="js/respond.min.js"></script>
<![endif]-->

</body>

</html>
