package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.AboutDAO;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.About;
import org.xiyoulinux.model.Blog;
import org.xiyoulinux.model.Events;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 11/5/16.
 */
@WebServlet(name = "AdminPreviewServlet", urlPatterns = "/admin/preview")
public class AdminPreviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("events".equals(request.getParameter("type"))) {
            if (request.getParameter("id") == null) {
                response.sendRedirect("/admin/events");
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
        } else if ("about".equals(request.getParameter("type"))) {
            if (request.getParameter("id") == null) {
                response.sendRedirect("/admin/about");
            } else {
                int id = 0;
                String str_id = request.getParameter("id");
                try {
                    id = Integer.parseInt(str_id);
                } catch (NumberFormatException e) {
                    response.sendRedirect("/404.html");
                }
                AboutDAO aboutDAO = new AboutDAO();
                About about = aboutDAO.getAboutByID(id);
                if (null == about) {
                    response.sendRedirect("/404.html");
                } else {
                    request.setAttribute("title", about.getTitle());
                    request.setAttribute("url", about.getPicture_url());
                    request.setAttribute("content", about.getContent());
                    request.setAttribute("markdown", about.getMarkdown());
                    request.getRequestDispatcher("/aboutview.jsp").forward(request, response);
                }
            }
        } else if ("blog".equals(request.getParameter("type"))) {
            if (request.getParameter("id") == null) {
                response.sendRedirect("/admin/blog");
            } else {
                int id = 0;
                String str_id = request.getParameter("id");
                try {
                    id = Integer.parseInt(str_id);
                } catch (NumberFormatException e) {
                    response.sendRedirect("/404.html");
                }
                BlogDAO blogDAO = new BlogDAO();
                Blog blog = blogDAO.getBlogByID(id);
                if (null == blog) {
                    response.sendRedirect("/404.html");
                } else {
                    request.setAttribute("title", blog.getTitle());
                    request.setAttribute("author", blog.getAuthor());
                    request.setAttribute("date", blog.getDate());
                    request.setAttribute("time", blog.getTime());
                    request.setAttribute("summary", blog.getSummary());
                    request.setAttribute("url", blog.getUrl());
                    request.getRequestDispatcher("/blogview.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
