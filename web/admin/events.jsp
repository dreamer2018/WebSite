<%@ page import="org.xiyoulinux.model.Events" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/28/16
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/images/xiyoulinux.png">
    <title>西邮Linux兴趣小组官网-后台管理</title>
    <!-- Bootstrap css -->
    <link href="http://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://o.qcloud.com/static_api/v3/assets/fontawesome/css/font-awesome.css" rel="stylesheet">
    <!-- 当前项目样式文件 -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <link href="css/sb-bk-theme.css" rel="stylesheet">
    <!--蓝鲸平台APP 公用的样式文件 -->
    <link href="http://o.qcloud.com/static_api/v3/assets/daterangepicker-2.0.5/daterangepicker.css" rel="stylesheet">
    <link href="http://o.qcloud.com/static_api/v3/bk/css/bk.css?v=1.0.1" rel="stylesheet">
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
            <a class="navbar-brand" href="index.html">
                <i class="fa fa-leaf f20 mr5"></i>
                后台管理系统
            </a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
            </li>
            <li class="dropdown">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> admin
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="javascript:;"><i class="fa fa-fw fa-user"></i> 用户</a>
                    </li>
                    <li>
                        <a href="javascript:;"><i class="fa fa-fw fa-envelope"></i> 消息盒</a>
                    </li>
                    <li>
                        <a href="javascript:;"><i class="fa fa-fw fa-gear"></i> 设置</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="javascript:;"><i class="fa fa-fw fa-power-off"></i> 退出</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li>
                    <a href="/admin/"><i class="fa fa-fw fa-dashboard"></i>首页</a>
                </li>
                <li class="active">
                    <a href="/admin/events"><i class="fa fa-fw fa-table"></i>活动管理</a>
                </li>
                <li>
                    <a href="/admin/blog"><i class="fa fa-fw fa-edit"></i>文章管理</a>
                </li>
                <li>
                    <a href="/admin/about"><i class="fa fa-fw fa-desktop"></i>简介管理</a>
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
                        活动管理
                    </h1>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    活动列表
                </div>
                <div>
                    <div class="king-wrapper">
                        <form class="form-inline king-search-form king-no-bg mt15 mb15 pull-left" style="margin-left: 2%">
                            <div class="form-group">
                                <label>标题：</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="请输入文章标题">
                                </div>
                            </div>
                            <div class="form-group" >
                                <label>日期：</label>
                                <div class="input-group">
                                    <input type="text" class="form-control daterangepicker_demo"
                                           id="daterangepicker_demo2" placeholder="选择日期...">
                                    <span class="input-group-addon"><i
                                            class="glyphicon glyphicon-time fa fa-calendar-o"></i></span>
                                </div>
                            </div>
                            <button type="submit" class="king-btn king-info">搜索</button>
                        </form>
                        <a href="/admin/eventsedit.jsp" class="king-btn king-info pull-right mt15 ml15" style="margin-right: 2%;">
                            <i class="fa fa-user-plus mr5"></i>新增文章
                        </a>
                    </div>

                    <table class="table table-out-bordered table-hover">
                        <thead>
                        <tr>
                            <th style="width:50px;" class="tc">序号</th>
                            <th >标题</th>
                            <th >日期</th>
                            <th >时间</th>
                            <th >地点</th>
                            <th >阅读量</th>
                            <th >状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<Events> eventsList = (ArrayList<Events>) request.getAttribute("eventsList");
                            for (int i = 0; i < eventsList.size(); i++) {
                        %>
                        <tr>
                            <td><%=i+1%></td>
                            <td >
                                <a href="/admin/eventsedit?id=<%=eventsList.get(i).getId()%>"><%=eventsList.get(i).getTitle()%>
                                </a>
                            </td>
                            <td ><%=eventsList.get(i).getDate()%>
                            </td>
                            <td ><%=eventsList.get(i).getTime()%>
                            </td>
                            <td ><%=eventsList.get(i).getAddress()%>
                            </td>
                            <td ><%=eventsList.get(i).getReader()%>
                            </td>
                            <td ><%=eventsList.get(i).getStatus()%>
                            </td>
                            <td >
                                <a href="/admin/delete?id=<%=eventsList.get(i).getId()%>&type=events" class="mr15">删除</a>
                                <a href="/admin/preview?id=<%=eventsList.get(i).getId()%>&type=events" target="_blank">预览</a>
                            </td>
                        <tr>
                                <%}%>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="12">
                                <div class="pagination-info pull-left">共<%=eventsList.size()%>条记录，当前第1/1页，每页20条记录</div>
                                <div class="pull-right king-page-box">
                                    <ul class="pagination pagination-small pull-right">
                                        <li page-index="1" class="disabled"><a>«</a></li>
                                        <li page-index="1" class="active"><a>1</a></li>
                                        <li page-index="1"><a href="javascript:;">2</a></li>
                                        <li page-index="1"><a href="javascript:;">3</a></li>
                                        <li page-index="1"><a href="javascript:;">4</a></li>
                                        <li page-index="1"><a href="javascript:;">»</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>

            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
<script src="http://o.qcloud.com/static_api/v3/assets/js/jquery-1.10.2.min.js"></script>
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
<script src="http://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/js/bootstrap.min.js"></script>


<script src="http://o.qcloud.com/static_api/v3/assets/daterangepicker-2.0.5/moment.min.js"></script>
<script src="http://o.qcloud.com/static_api/v3/assets/daterangepicker-2.0.5/daterangepicker.js"></script>


<script type="text/javascript">
    // 选择日期范围
    $('#daterangepicker_demo2').daterangepicker({
        locale: {
            "format": 'YYYY/MM/DD'
        }
    });
</script>

<!--[if lt IE 9]>
<script src="http://o.qcloud.com/static_api/v3/assets/js/html5shiv.min.js"></script>
<script src="http://o.qcloud.com/static_api/v3/assets/js/respond.min.js"></script>
<![endif]-->

</body>

</html>

