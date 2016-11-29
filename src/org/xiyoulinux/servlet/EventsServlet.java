package org.xiyoulinux.servlet;

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
@WebServlet(name = "EventsServlet",urlPatterns = "/admin/events")
public class EventsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("page") == null) {
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
        }else {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
