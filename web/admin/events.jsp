<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/3/16
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.xiyoulinux.model.Events" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>活动管理</title>

    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="background-color: rgba(235, 232, 236, 0.55)">
    <div class="row" style="background-color: rgba(223, 220, 224, 0.55)">
        <div class="col-xs-2">
            <h1>活动列表</h1>
        </div>
        <div class="col-xs-1">
            <a href="eventsedit.jsp">
                <button type="button" class="btn btn-success" style="margin-top: 26px;">创建新活动</button>
            </a>
        </div>
        <div class="col-xs-1 pull-right" style="margin-top: 26px;">
            <div class="input-group">
                <a href="/admin">
                    <button class="btn btn-success" type="button">返回</button>
                </a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-responsive">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>日期</th>
                    <th>时间</th>
                    <th>地点</th>
                    <th>预览</th>
                    <th>删除</th>
                </tr>
                </thead>
                <%
                    ArrayList<Events> eventsList = (ArrayList<Events>) request.getAttribute("eventsList");
                    for(int i=0; i < eventsList.size(); i++){
                        out.println(eventsList.get(i).getId());
                %>
                <tr>
                    <td><a href="/admin/events?id=">纳新</a></td>
                    <td>2016-09-03 12:00:00</td>
                    <td>西安邮电大学长安校区东区</td>
                    <td>纳新，学妹</td>
                    <td>
                        <form method="post" action="#">
                            <input type="hidden" value="1">
                            <input type="submit" value="预览" class="btn btn-primary">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="#">
                            <input type="hidden" value="1">
                            <input type="submit" value="删除" class="btn btn-danger">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
                <%--<tr>
                    <td><a href="eventsedit.jsp">纳新</a></td>
                    <td>2016-09-03 12:00:00</td>
                    <td>西安邮电大学长安校区东区</td>
                    <td>纳新，学妹</td>
                    <td>
                        <form method="post" action="#">
                            <input type="hidden" value="1">
                            <input type="submit" value="预览" class="btn btn-primary">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="#">
                            <input type="hidden" value="1">
                            <input type="submit" value="删除" class="btn btn-danger">
                        </form>
                    </td>
                </tr>--%>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>