package org.xiyoulinux.servlet.admin;

import org.json.JSONObject;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.dao.EventsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 1/13/17.
 */
@WebServlet(name = "AdminStatusServlet", urlPatterns = "/admin/status")
public class AdminStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("events".equals(request.getParameter("type"))) {
            String str_id = request.getParameter("id");
            try {
                int eventsid = Integer.parseInt(str_id);
                EventsDAO eventsDAO = new EventsDAO();
                boolean status = eventsDAO.alterEventsStatus(eventsid);
                JSONObject json = new JSONObject();
                json.put("status", status);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json.toString());
            } catch (NumberFormatException e) {
                response.sendRedirect("/404.html");
            }
        }else if ("blog".equals(request.getParameter("type"))){
            String str_id = request.getParameter("id");
            try {
                int blogid = Integer.parseInt(str_id);
                BlogDAO blogDAO = new BlogDAO();
                boolean status = blogDAO.alterBlogStatus(blogid);
                JSONObject json = new JSONObject();
                json.put("status", status);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json.toString());
            } catch (NumberFormatException e) {
                response.sendRedirect("/404.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
