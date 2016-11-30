<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.xiyoulinux.dao.BlogDAO" %>
<%@ page import="org.xiyoulinux.dao.EventsDAO" %>
<%@ page import="org.xiyoulinux.model.Blog" %>
<%@ page import="org.xiyoulinux.model.Events" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.xiyoulinux.util.HtmlUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/30/16
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/images/xiyoulinux.png">
    <title>西邮Linux兴趣小组</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>

<body>
<!--导航栏-->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-logo" href="#"><img src="/images/xiyoulinux.png"
                                                 style="width: 50px;height: 50px;">西邮Linux兴趣小组</a>
        </div>
        <div class="navbar-collapse collapse navbar-right">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.html">HOME</a></li>
                <li><a href="all_events.html">EVENTS</a></li>
                <li><a href="http://blog.xiyoulinux.org">BLOG</a></li>
                <li><a href="login.jsp">LOGIN</a></li>
                <li><a href="about.html">ABOUT</a></li>
            </ul>
        </div>
    </div>
</div>
<!--导航栏结束-->

<div id="service">
    <div class="container">
        <div class="row centered" id="service-list">
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="http://blog.xiyoulinux.org"><img src="/images/mirrors.png"></a>
                <h4>群博<a href="http://blog.xiyoulinux.org"><i class="fa  fa-arrow-circle-right"></i></a></h4>
                <p>这里是技术的天堂</p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="http://wiki.xiyoulinux.org"><img src="/images/web-irc.png"></a>
                <h4>WIKI<a href="http://wiki.xiyoulinux.org"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p>积累的力量</p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="IRC.html"><img src="/images/moinmoin.png"></a>
                <h4>IRC<a href="IRC.html"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p>来这里聊天</p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="https://groups.google.com/forum/#!forum/xiyoulinux"><img src="/images/calendar.png"></a>
                <h4>邮件列表 <a href="https://groups.google.com/forum/#!forum/xiyoulinux"><i
                        class="fa fa-arrow-circle-right"></i></a></h4>
                <p>不要错过我们的活动</p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="#"><img src="/images/danmaku.png"></a>
                <h4>小组资源 <a href="#"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p>互动演讲</p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <a href="#"><img src="/images/issues.png"></a>
                <h4>Issues <a href="#"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p>Bug 提交/意见反馈</p>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div id="recent-events" class="col-md-6 col-md-push-6">
            <h2> # 活动公告 </h2>
            <%
                EventsDAO eventsDAO = new EventsDAO();
                ArrayList<Events> eventss = eventsDAO.getEventsByNumber(5);
                for (Events events : eventss) {
            %>
            <div class="event" data-event-date="2016-05-29">
                <div class="event-title">
                    <a href="/events?id=<%=events.getId()%>" target="_blank">
                        <h4><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;<%=events.getTitle()%>
                        </h4>
                    </a>
                </div>
                <div class="event-meta">
                    <span><strong>时间: </strong><%=events.getDate()%>&nbsp;&nbsp;<%=events.getTime()%></span>
                    <span><strong>地点:</strong><%=events.getAddress()%></span>
                    <%
                        String label = events.getLabel();
                        String str = "";
                        for (int i = 0; i < label.length(); i++) {
                            if (label.charAt(i) == ',' || label.charAt(i) == '，') {
                                if (!(str.equals("") || str.equals(",") || str.equals("，"))) {
                    %>
                    <span class="label label-info"><%=str%></span>
                    <%
                                }
                                str = "";
                            } else {
                                str += label.charAt(i);
                            }
                        }
                        if (!(str.equals("") || str.equals(",") || str.equals("，"))) {
                    %>
                    <span class="label label-info"><%=str%></span>
                    <%
                        }
                    %>
                </div>
                <div class="event-abstract">
                    <%=HtmlUtil.getTextFromTHML(events.getContent().substring(0, 300))%>&nbsp;<a href="/events?id=<%=events.getId()%>" target="_blank">>>></a>
                </div>
            </div>
            <%
                }
            %>
            <div>
                <a href="all_events.html"><i class="fa fa-arrow-circle-right"></i> 所有活动</a>
            </div>
        </div>
        <div id="recent-posts" class="col-md-6 col-md-pull-6">
            <h2> # 最近文章 </h2>
            <%
                BlogDAO blogDAO = new BlogDAO();
                ArrayList<Blog> blogs = blogDAO.getBlogByNumber(5);
                for (Blog blog : blogs) {
            %>
            <div class="post">
                <div class="post-title">
                    <a href="http://blog.xiyoulinux.org/<%=blog.getUrl()%>" target="_blank">
                        <h4><i class="fa fa-file-text-o"></i>&nbsp;&nbsp;<%=blog.getTitle()%>
                        </h4>
                    </a>
                </div>
                <div class="post-meta">
                    <%=blog.getAuthor()%>,&nbsp;&nbsp; <%=blog.getDate()%>&nbsp;&nbsp;<%=blog.getTime()%>
                </div>
                <div class="post-abstract">
                    <%=HtmlUtil.getTextFromTHML(blog.getSummary())%>&nbsp;<a href="http://blog.xiyoulinux.org/<%=blog.getUrl()%>">>>></a>
                </div>
            </div>
            <%}%>
            <div>
                <a href="http://blog.xiyoulinux.org"><i class="fa fa-arrow-circle-right"></i> 所有文章</a>
            </div>
        </div>
    </div>
</div>

<!--网站底部-->
<div id="footerwrap">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <p class="heading">链接</p>
                <ul>
                    <li><a href="http://222.24.19.3:81/xiyoucs/index.asp">西安邮电大学计算机学院</a></li>
                    <li><a href="http://www.kerneltravel.net/">Linux内核之旅</a></li>
                </ul>
            </div>
            <div class="col-sm-3">
                <p class="heading">社区</p>
                <ul>
                    <li>邮件列表: <a href="https://groups.google.com/forum/#!forum/xiyoulinux">xiyoulinux</a></li>
                    <li>新浪微博: <a href="http://weibo.com/xylinux">@西邮Linux兴趣小组</a></li>
                    <li>人人主页: <a href="http://page.renren.com/601367653?checked=true">@西邮Linux兴趣小组</a></li>
                </ul>
            </div>
            <div class="col-sm-3">
                <p class="heading">地址</p>
                <ul>
                    <li>
                        陕西省 西安市 长安区<br>
                        西安邮电大学长安校区<br>
                        东区 教学实验楼 FZ118<br>
                        邮编：710121
                    </li>
                </ul>
            </div>
            <div class="col-sm-3">
                <p class="heading">关注我们</p>
                <ul>
                    <li>
                        <img class="qr" src="https://www.xiyoulinux.org/assets/images/weixin.jpg"
                             style="width: 80px;height: 80px;">
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--网站底部-->

<script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/retina-1.1.0.js"></script>
<script type="text/javascript" src="/js/jquery.hoverdir.js"></script>
<script type="text/javascript" src="/js/jquery.hoverex.min.js"></script>
<script type="text/javascript" src="/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="/js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="/js/custom.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var today = function () {
            var pad = function (number) {
                var r = String(number);
                if (r.length === 1) {
                    r = '0' + r;
                }
                return r;
            }
            var _today = new Date();
            return Date.parse(
                _today.getFullYear().toString() + "-"
                + pad(_today.getMonth() + 1) + "-"
                + pad(_today.getDate())
            );
        }();
        $('#recent-events .event').each(function () {
            // console.log($(this).attr("data-event-date"));
            var event_date = Date.parse($(this).attr("data-event-date")),
                label = "";
            if (today > event_date) {
                label = "<span class='label label-info'>Finished</span>";
            } else {
                label = "<span class='label label-warning'>Upcoming</span>";
            }
            $(this).find(".event-meta").append(label);
        });
    });
</script>

</body>
</html>
