<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.xiyoulinux.dao.BlogDAO" %>
<%@ page import="org.xiyoulinux.dao.EventsDAO" %>
<%@ page import="org.xiyoulinux.model.Blog" %>
<%@ page import="org.xiyoulinux.model.Events" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.xiyoulinux.util.HtmlUtil" %>
<%@ page import="org.xiyoulinux.dao.TitleDAO" %>
<%@ page import="org.xiyoulinux.model.Title" %>
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
    <link rel="shortcut icon" href="images/xiyoulinux.png">
    <title>西邮Linux兴趣小组</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
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
            <a class="navbar-logo" href="#"><img src="images/xiyoulinux.png"
                                                 style="width: 50px;height: 50px;">西邮Linux兴趣小组</a>
        </div>
        <div class="navbar-collapse collapse navbar-right">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/index.jsp">HOME</a></li>
                <li><a href="/events">EVENTS</a></li>
                <li><a href="http://blog.xiyoulinux.org">BLOG</a></li>
                <li><a href="login.jsp">LOGIN</a></li>
                <li><a href="/about">ABOUT</a></li>
            </ul>
        </div>
    </div>
</div>
<!--导航栏结束-->

<div id="service">
    <div class="container">
        <%
            TitleDAO titleDAO = new TitleDAO();
            Title title1 = titleDAO.getTitleById(1);
            Title title2 = titleDAO.getTitleById(2);
            Title title3 = titleDAO.getTitleById(3);
            Title title4 = titleDAO.getTitleById(4);
            Title title5 = titleDAO.getTitleById(5);
            Title title6 = titleDAO.getTitleById(6);
        %>
        <div class="row centered" id="service-list">
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title1.getUrl()%>">
                        <div class="inicon blogicon"></div>
                    </a>
                </div>
                <h4><%=title1.getTitle()%><a href="<%=title1.getUrl()%>"><i class="fa  fa-arrow-circle-right"></i></a></h4>
                <p><%=title1.getSubtitle()%></p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title2.getUrl()%>">
                        <div class="inicon wikiicon"></div>
                    </a>
                </div>
                <h4><%=title2.getTitle()%><a href="<%=title2.getUrl()%>"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p><%=title2.getSubtitle()%></p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title3.getUrl()%>">
                        <div class="inicon ircicon"></div>
                    </a>
                </div>
                <h4><%=title3.getTitle()%><a href="<%=title3.getUrl()%>"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p><%=title3.getSubtitle()%></p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title4.getUrl()%>">
                        <div class="inicon mailicon"></div>
                    </a>
                </div>
                <h4><%=title4.getTitle()%><a href="<%=title4.getUrl()%>"><i
                        class="fa fa-arrow-circle-right"></i></a></h4>
                <p><%=title4.getSubtitle()%></p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title5.getUrl()%>">
                        <div class="inicon resourceicon"></div>
                    </a>
                </div>
                <h4><%=title5.getTitle()%><a href="<%=title5.getUrl()%>"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p><%=title5.getSubtitle()%></p>
            </div>
            <div class="col-md-2 col-sm-3 col-xs-6">
                <div class="hexicon">
                    <a href="<%=title6.getUrl()%>">
                        <div class="inicon othericon"></div>
                    </a>
                </div>
                <h4><%=title6.getTitle()%><a href="<%=title6.getUrl()%>"><i class="fa fa-arrow-circle-right"></i></a></h4>
                <p><%=title6.getSubtitle()%></p>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">

        <div id="recent-posts" class="col-md-6 col-md-push-6">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4>最近文章</h4>
                </div>
                <div class="panel-body">
                    <%
                        BlogDAO blogDAO = new BlogDAO();
                        ArrayList<Blog> blogs = blogDAO.getBlogByPage(1,"",5);
                        for (Blog blog : blogs) {
                    %>
                    <div class="post">
                        <div class="post-title">
                            <a href="<%=blog.getUrl()%>" target="_blank">
                                <h4><i class="fa fa-file-text-o"></i>&nbsp;&nbsp;<%=blog.getTitle()%>
                                </h4>
                            </a>
                        </div>
                        <div class="post-meta">
                            <%=blog.getAuthor()%>,&nbsp;&nbsp; <%=blog.getDate()%>&nbsp;&nbsp;<%=blog.getTime()%>
                        </div>
                        <div class="post-abstract">
                            <%
                                if (HtmlUtil.getTextFromTHML(blog.getSummary()).length() <= 250) {
                            %>
                            <%=HtmlUtil.getTextFromTHML(blog.getSummary())%>&nbsp;<a
                                href="<%=blog.getUrl()%>">>>></a>
                            <%for(int i=0;i<(250-HtmlUtil.getTextFromTHML(blog.getSummary()).length())/38+1;i++){%>
                                <br/>
                            <%}%>
                            <%} else {%>
                            <%=HtmlUtil.getTextFromTHML(blog.getSummary()).substring(0, 250)%>&nbsp;<a
                                href="<%=blog.getUrl()%>">>>></a>
                            <%}%>
                        </div>
                    </div>
                    <br/>
                    <%}%>
                    <div>
                        <a href="http://blog.xiyoulinux.org"><i class="fa fa-arrow-circle-right"></i> 所有文章</a>
                    </div>
                </div>
            </div>
        </div>
        <div id="recent-events" class="col-md-6 col-md-pull-6">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h4>活动公告</h4>
                </div>
                <div class="panel-body">
                    <%
                        EventsDAO eventsDAO = new EventsDAO();
                        ArrayList<Events> eventss = eventsDAO.getEventsByPage(1,"",5);
                        for (Events events : eventss) {
                    %>
                    <div class="event" data-event-date="<%=events.getDate()%>%>">
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
                            <%if(HtmlUtil.getTextFromTHML(events.getContent()).length() <= 250){%>
                            <%=HtmlUtil.getTextFromTHML(events.getContent())%>&nbsp;<a
                                href="/events?id=<%=events.getId()%>" target="_blank">>>></a>
                            <%for(int i=0;i<(250-HtmlUtil.getTextFromTHML(events.getContent()).length())/38+1;i++){%>
                            <br/>
                            <%}%>
                            <%}else{%>
                            <%=HtmlUtil.getTextFromTHML(events.getContent()).substring(0,250)%>&nbsp;<a
                                href="/events?id=<%=events.getId()%>" target="_blank">>>></a>
                            <%}%>
                        </div>
                    </div>
                    <br/>
                    <%
                        }
                    %>
                    <div>
                        <a href="/events"><i class="fa fa-arrow-circle-right"></i> 所有活动</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--网站底部-->
<footer>
    <div class="container">
        <div class="content">
            <div class="row">
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">链接</p>
                        <ul>
                            <li><a href="http://www.xupt.edu.cn/">西安邮电大学</a></li>
                            <li><a href="http://cs.xupt.edu.cn">西安邮电大学计算机学院</a></li>
                            <li><a href="http://www.kerneltravel.net/">Linux内核之旅</a></li>
                            <li><a href="https://www.kernel.org/">The Linux Kernel Archives</a></li>
                            <li><a href="https://www.linuxfoundation.org/">The Linux Foundation</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">社区</p>
                        <ul>
                            <li>邮件列表: <a href="https://groups.google.com/forum/#!forum/xiyoulinux">xiyoulinux</a></li>
                            <li>新浪微博: <a href="http://weibo.com/xylinux">@西邮Linux兴趣小组</a></li>
                            <li>人人主页: <a href="http://page.renren.com/601367653?checked=true">@西邮Linux兴趣小组</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">地址</p>
                        <ul>
                            <li>
                                陕西省 西安市 长安区<br>
                                西安邮电大学长安校区<br>
                                东区 教学实验楼 FZ118<br>
                                邮编：710121
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 text-center">
                    <div class="link-address">
                        <p class="head">关注我们</p>
                        <ul>
                            <li>
                                <img class="qr" src="images/weixin.jpg">
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="copy-right-content">
          <span>
             Copyright © 2006 - <span class="year"></span> 西邮Linux兴趣小组 All Rights Reserved
          </span>
        </div>
    </div>
</footer>
<!--网站底部-->

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
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
            var event_date = Date.parse($(this).attr("data-event-date")),
                label = "";
            if (today > event_date) {
                label = "<span class='label label-default'>Finished</span>";
            } else if (today < event_date) {
                label = "<span class='label label-warning'>Upcoming</span>";
            } else {
                label = "<span class='label label-primary'>Finishing</span>";
            }
            $(this).find(".event-meta").append(label);
        });
        $('.container .copy-right-content').each(function () {
            var year = new Date().getFullYear();
            $(this).find(".year").append(year);
        })
    });
</script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/retina-1.1.0.js"></script>
<script type="text/javascript" src="js/jquery.hoverdir.js"></script>
<script type="text/javascript" src="js/jquery.hoverex.min.js"></script>
<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript" src="js/main.js"></script>


</body>
</html>
