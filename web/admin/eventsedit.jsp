<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/3/16
  Time: 16:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>活动编辑</title>
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
            <h3>撰写新文章</h3>
        </div>
    </div>
    <br/>
    <div class="row">
        <form action="/admin/events/" method="post">
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">标题：</span>
                        <input type="text" class="form-control" name="title" placeholder="在此输入标题">
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group ">
                        <span class="input-group-addon">日期：</span>
                        <input type="text" class="form-control" name="date" id="pickdate" placeholder="在此输入日期">
                    </div>
                </div>
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">时间：</span>
                        <input type="text" class="form-control" name="time" id="picktime" placeholder="在此输入时间">
                    </div>
                </div>
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">地点：</span>
                        <input type="text" class="form-control" name="address" placeholder="在此输入地点">
                    </div>
                </div>
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">标签：</span>
                        <input type="text" class="form-control" name="label" placeholder="多个标签逗号隔开">
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <span class="input-group-addon">海报url：</span>
                        <input type="text" class="form-control" name="poster" placeholder="多个标签逗号隔开">
                    </div>
                </div>
            </div>
            <br/>
            <div id="test-editormd">
                <textarea style="display:none;width: 97%"></textarea>
            </div>
            <input type="hidden" name="markdown" id="markdown">
            <input type="hidden" name="content" id="content">
            <br/>
            <div class="row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <input type="submit" class="btn btn-success" value="提交" name="submit" onclick="getHtml()">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>


<!--editor-md 依赖 -->
<script src="js/jquery.min.js"></script>
<script src="js/editormd.min.js"></script>
<script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd("test-editormd", {
            width: "100%",
            height: 640,
            syncScrolling: "single",
            path: "../lib/",
            saveHTMLToTextarea: true,
            onfullscreen: function () {
                input = document.getElementsByClassName("form-control");
                for (var i = 0; i < input.length; i++) {
                    input[i].type = "hidden";
                }
                input = document.getElementsByName("submit");
                for (i = 0; i < input.length; i++) {
                    input[i].type = "hidden";
                }
            },
            onfullscreenExit: function () {
                input = document.getElementsByClassName("form-control");
                for (var i = 0; i < input.length; i++) {
                    input[i].type = "text";
                }
                input = document.getElementsByName("submit");
                for (i = 0; i < input.length; i++) {
                    input[i].type = "submit";
                }
            }
        });
    });

    function getHtml() {
        alert(testEditor.getHTML());
        content = document.getElementById("content");
        content.value = testEditor.getHTML();
        md = document.getElementById("markdown");
        md.value = testEditor.getMarkdown();
        alert(testEditor.getMarkdown());
    }

</script>


<!--bootstrap依赖-->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>


<!--datepicter的依赖-->
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/timedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datepicker.js"></script>


</body>
</html>