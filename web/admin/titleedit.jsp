<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/3/16
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>标题编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/datedropper.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/timedropper.min.css">
    <link rel="stylesheet" href="/admin/css/editormd.css"/>
</head>
<body>

<div class="container" style="background-color: rgba(235, 232, 236, 0.55)">
    <div class="row">
        <div>
            <h3>标题编辑</h3>
        </div>
    </div>
    <br/>
    <div class="row">
        <form action="/admin/titleedit" method="post" onsubmit="return check()">
            <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">标题：</span>
                        <input type="text" class="form-control" name="title" id="title" placeholder="在此输入标题"
                               value="<%if(null != request.getAttribute("title")){out.print(request.getAttribute("title"));}%>">
                    </div>
                </div>
                <div class="col-xs-3" style="color: #9d1108;font-size: large">
                    <%if(null != request.getAttribute("message")){out.print(request.getAttribute("message"));}%>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">副标题：</span>
                        <input type="text" class="form-control" name="subtitle" id="subtitle" placeholder="在此输入副标题"
                               value="<%if(null != request.getAttribute("title")){out.print(request.getAttribute("subtitle"));}%>">
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">链接：</span>
                        <input type="text" class="form-control" name="url" id="url" placeholder="在此输入url"
                               value="<%if(null != request.getAttribute("url")){out.print(request.getAttribute("url"));}%>">
                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="submit" class="btn btn-success" name="submit" value="提交">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="application/javascript">
    function check(){
        var title = document.getElementById("title").value;
        var subtitle = document.getElementById("subtitle").value;
        var url = document.getElementById("url").value;
        if(title.trim().length <= 0){
            alert("标题不能为空!");
            return false;
        }else if(title.trim().length > 20){
            alert("标题不能超过20个字符!");
            return false;
        } else if(subtitle.trim().length <= 0 ){
            alert("副标题不能为空！");
            return false;
        }else if(subtitle.trim().length > 30) {
            alert("副标题不能超过30个字符!");
            return false;
        }else if(url.trim().length <= 0 ){
            alert("url不能为空！");
            return false;
        }else if (url.trim().length > 256 ){
            alert("url过长！");
            return false;
        }
        return true;
    }
</script>
<!--bootstrap依赖-->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>