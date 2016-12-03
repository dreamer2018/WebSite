package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.Events;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 11/30/16.
 */
@WebServlet(name = "AdminEventsViewServlet",urlPatterns = "/events")
public class AdminEventsViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
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
            System.out.println(events);
            if (null == events) {
                response.sendRedirect("/404.html");
            } else {
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
