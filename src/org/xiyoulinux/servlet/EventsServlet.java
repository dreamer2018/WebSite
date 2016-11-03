package org.xiyoulinux.servlet;

import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.Events;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.Time;


/**
 * Created by zhoupan on 11/3/16.
 */
@WebServlet(name = "EventsServlet", urlPatterns = "/admin/events/")
public class EventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean error = false;
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String address = request.getParameter("address");
        String label = request.getParameter("label");
        String poster = request.getParameter("poster");
        String mkdown = request.getParameter("markdown");
        String content = request.getParameter("content");
        if(title == null || title.equals("")){
            request.setAttribute("title","标题不能为空！");
            error = true;
        }
        if(date ==  null || date.equals("")){
            request.setAttribute("date","日期不能为空！");
            error = true;
        }
        if(time ==  null || time.equals("")){
            request.setAttribute("time","日期不能为空！");
            error = true;
        }
        if(address ==  null || address.equals("")){
            request.setAttribute("address","日期不能为空！");
            error = true;
        }
        if(label ==  null || label.equals("")){
            request.setAttribute("label","日期不能为空！");
            error = true;
        }
        if(poster ==  null || poster.equals("")){
            request.setAttribute("poster","日期不能为空！");
            error = true;
        }
        if(mkdown ==  null || mkdown.equals("")){
            request.setAttribute("mkdown","日期不能为空！");
            error = true;
        }
        if(content ==  null || content.equals("")){
            request.setAttribute("content","日期不能为空！");
            error = true;
        }
        System.out.println(mkdown);
        if(error){
            response.sendRedirect("/404.html");
        }else {
            Events events = new Events();
            events.setTitle(title);
            events.setContent(content);
            events.setDate(date);
            events.setTime(time);
            events.setPoster_url(poster);
            events.setAddress(address);
            events.setTips(label);
            EventsDAO eventsDAO = new EventsDAO();
            if(eventsDAO.insert(events)){
                response.sendRedirect("/admin/events.jsp");
            }else {
                response.sendRedirect("/404.html");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
