package org.xiyoulinux.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xiyoulinux.dao.EventsDAO;

import org.xiyoulinux.model.Events;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 11/30/16.
 */
@WebServlet(name = "EventsViewServlet", urlPatterns = "/events")
public class EventsViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            if (null != request.getParameter("page") && "ajax".equals(request.getParameter("type"))) {
                String str_page = request.getParameter("page");
                int page = 2;
                try {
                    page = Integer.parseInt(str_page);
                } catch (NumberFormatException e) {
                    response.sendRedirect("/404.html");
                }
                EventsDAO eventsDAO = new EventsDAO();
                ArrayList<Events> eventsList = eventsDAO.getEventsByPage(page, "", 2);
                if (eventsList.size() <= 0) {
                    response.getWriter().write("{}");
                } else {
                    int pageCount = eventsDAO.getAllPageCount();
                    int currentPage = eventsDAO.getCurrentPage();
                    int allCount = eventsDAO.getAllCount();
                    JSONObject json = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    json.put("pageCount", pageCount);
                    json.put("currPage", currentPage);
                    json.put("allCount", allCount);
                    for (Events events : eventsList) {
                        eventsDAO.addEventsRead(events.getId());
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id",events.getId());
                        jsonObject.put("title", events.getTitle());
                        jsonObject.put("date", events.getDate());
                        jsonObject.put("time", events.getTime());
                        jsonObject.put("address", events.getAddress());
                        jsonObject.put("label", events.getLabel());
                        jsonObject.put("content", events.getContent());
                        jsonObject.put("poster", events.getPoster());
                        jsonObject.put("reader", events.getReader());
                        jsonArray.put(jsonObject);
                    }
                    json.put("eventsList", jsonArray);
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(json.toString());
                }
            } else {
                EventsDAO eventsDAO = new EventsDAO();
                ArrayList<Events> list = eventsDAO.getNewEvents();
                if (null == list) {
                    response.sendRedirect("/404.html");
                } else {
                    request.setAttribute("eventsList", list);
                    request.getRequestDispatcher("/events.jsp").forward(request, response);
                }
            }
        } else {
            int id = 0;
            String str_id = request.getParameter("id");
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                response.sendRedirect("/404.html");
            }
            EventsDAO eventsDAO = new EventsDAO();
            Events events = eventsDAO.getEventsByID(id);
            if (null == events) {
                response.sendRedirect("/404.html");
            } else {
                eventsDAO.addEventsRead(events.getId());
                request.setAttribute("title", events.getTitle());
                request.setAttribute("date", events.getDate());
                request.setAttribute("time", events.getTime());
                request.setAttribute("address", events.getAddress());
                request.setAttribute("label", events.getLabel());
                request.setAttribute("content", events.getContent());
                request.setAttribute("poster", events.getPoster());
                request.setAttribute("reader", events.getReader());
                request.getRequestDispatcher("/eventsview.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
