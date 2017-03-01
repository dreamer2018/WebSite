package org.xiyoulinux.servlet.admin;

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
 * Created by zhoupan on 11/3/16.
 */
@WebServlet(name = "AdminEventsServlet", urlPatterns = "/admin/events")
public class AdminEventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("page") == null) {
            EventsDAO eventsDAO = new EventsDAO();
            ArrayList eventsList = eventsDAO.getEventsByPage(1, "");
            int pageCount = eventsDAO.getAllPageCount();
            int currentPage = eventsDAO.getCurrentPage();
            int allCount = eventsDAO.getAllCount();
            request.setAttribute("eventsList", eventsList);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.getRequestDispatcher("/admin/events.jsp").forward(request, response);
        } else {
            if ("ajax".equals(request.getParameter("type"))) {
                String title;
                String pagestring = request.getParameter("page");
                int page = Integer.parseInt(pagestring);
                if (null == request.getParameter("title")) {
                    title = "";
                } else {
                    title = request.getParameter("title");
                }

                EventsDAO eventsDAO = new EventsDAO();
                ArrayList<Events> eventsList = eventsDAO.getEventsByPage(page, title);
                int pageCount = eventsDAO.getAllPageCount();
                int currentPage = eventsDAO.getCurrentPage();
                int allCount = eventsDAO.getAllCount();
                JSONObject json = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                json.put("pageCount", pageCount);
                json.put("currPage", currentPage);
                json.put("allCount", allCount);
                json.put("title", title);

                for (Events events : eventsList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id",events.getId());
                    jsonObject.put("title", events.getTitle());
                 //   jsonObject.put("content", events.getContent());
                 //   jsonObject.put("markdown", events.getMarkdown());
                 //   jsonObject.put("poster", events.getPoster());
                    jsonObject.put("date", events.getDate());
                    jsonObject.put("time", events.getTime());
                    jsonObject.put("address", events.getAddress());
                //    jsonObject.put("label", events.getLabel());
                    jsonObject.put("reader", events.getReader());
                    jsonObject.put("status", events.getStatus());
                    jsonArray.put(jsonObject);
                }
                json.put("eventsList",jsonArray);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json.toString());
            } else {
                EventsDAO eventsDAO = new EventsDAO();
                ArrayList eventsList = eventsDAO.getEventsByPage((Integer) request.getAttribute("currentPage"), "");
                int pageCount = eventsDAO.getAllPageCount();
                int currentPage = eventsDAO.getCurrentPage();
                int allCount = eventsDAO.getAllCount();
                request.setAttribute("eventsList", eventsList);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("allCount", allCount);
                request.getRequestDispatcher("/admin/events.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
