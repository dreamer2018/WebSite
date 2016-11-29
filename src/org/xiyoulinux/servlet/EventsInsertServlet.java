package org.xiyoulinux.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.Events;

/**
 * Created by zhoupan on 11/3/16.
 */

@WebServlet(name = "EventsInsertServlet", urlPatterns = "/admin/eventsedit")
public class EventsInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (null == request.getParameter("id")) {
            boolean error = false;
            String title = request.getParameter("title");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String address = request.getParameter("address");
            String label = request.getParameter("label");
            String poster = request.getParameter("poster");
            String mkdown = request.getParameter("markdown");
            String content = request.getParameter("content");
            String str_sign = request.getParameter("status");
            if (title == null || title.equals("")) {
                request.setAttribute("message", "标题不能为空！");
                error = true;
            }else if (date == null || date.equals("")) {
                request.setAttribute("message", "日期不能为空！");
                error = true;
            }else if (time == null || time.equals("")) {
                request.setAttribute("message", "时间不能为空！");
                error = true;
            }else if (address == null || address.equals("")) {
                request.setAttribute("message", "地址不能为空！");
                error = true;
            }else if (label == null || label.equals("")) {
                request.setAttribute("message", "标签不能为空！");
                error = true;
            }else if (poster == null || poster.equals("")) {
                request.setAttribute("message", "海报URL不能为空！");
                error = true;
            }else if (mkdown == null || mkdown.equals("")) {
                request.setAttribute("message", "内容不能为空！");
                error = true;
            }else if (content == null || content.equals("")) {
                request.setAttribute("message", "内容不能为空！");
                error = true;
            }
            if (error) {
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("poster", poster);
                request.setAttribute("mkdown", mkdown);
                request.setAttribute("date", date);
                request.setAttribute("time", time);
                request.setAttribute("address", address);
                request.setAttribute("label", label);
                System.out.println("sign:"+str_sign);
                int sign = 0;
                try {
                    sign = Integer.parseInt(str_sign);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if(sign != 0){
                    request.setAttribute("id",sign);
                }
                request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
            } else {
                int sign = 0;
                try {
                    sign = Integer.parseInt(str_sign);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (sign == 0) {
                    Events events = new Events();
                    events.setTitle(title);
                    events.setContent(content);
                    events.setDate(date);
                    events.setTime(time);
                    events.setMarkdown(mkdown);
                    events.setPoster(poster);
                    events.setAddress(address);
                    events.setLabel(label);
                    EventsDAO eventsDAO = new EventsDAO();
                    if (eventsDAO.insert(events)) {
                        response.sendRedirect("/admin/events");
                    } else {
                        response.sendRedirect("/404.html");
                    }
                } else {
                    Events events = new Events();
                    events.setId(sign);
                    events.setTitle(title);
                    events.setContent(content);
                    events.setDate(date);
                    events.setTime(time);
                    events.setMarkdown(mkdown);
                    events.setPoster(poster);
                    events.setAddress(address);
                    events.setLabel(label);
                    EventsDAO eventsDAO = new EventsDAO();
                    if (eventsDAO.update(events)) {
                        response.sendRedirect("/admin/events");
                    } else {
                        response.sendRedirect("/404.html");
                    }
                }
            }
        } else {
            String str_id = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                response.sendRedirect("/admin/eventsedit.jsp");
            }
            EventsDAO eventsDAO = new EventsDAO();
            Events events = eventsDAO.getEventsByID(id);
            if (events == null) {
                response.sendRedirect("/admin/eventsedit.jsp");
            } else {
                request.setAttribute("id", events.getId());
                request.setAttribute("title", events.getTitle());
                request.setAttribute("content", events.getContent());
                request.setAttribute("poster", events.getPoster());
                request.setAttribute("mkdown", events.getMarkdown());
                request.setAttribute("date", events.getDate());
                request.setAttribute("time", events.getTime());
                request.setAttribute("address", events.getAddress());
                request.setAttribute("label", events.getLabel());
                request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
