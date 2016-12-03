package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.Blog;
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
@WebServlet(name = "AdminIndexServlet",urlPatterns = "")
public class AdminIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Blog> blogs = null;
        ArrayList<Events> eventss = null;
        BlogDAO blogDAO = new BlogDAO();
        EventsDAO eventsDAO = new EventsDAO();
        blogs = blogDAO.getBlogByNumber(5);
        eventss = eventsDAO.getEventsByNumber(5);
        request.setAttribute("blogs", blogs);
        request.setAttribute("eventss", eventss);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
