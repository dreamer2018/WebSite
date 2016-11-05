package org.xiyoulinux.servlet;

import org.xiyoulinux.dao.EventsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 11/5/16.
 */
@WebServlet(name = "EventsDeleteServlet",urlPatterns = "/admin/delete")
public class EventsDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id") == null){
            response.sendRedirect("/admin/events");
        }else{
            int id=0;
            String str_id = request.getParameter("id");
            try {
                id = Integer.parseInt(str_id);
            }catch (NumberFormatException e){
                response.sendRedirect("/404.html");
            }
            EventsDAO eventsDAO = new EventsDAO();
            if(eventsDAO.delete(id)){
                request.setAttribute("message","删除成功！");
                request.getRequestDispatcher("/admin/events").forward(request,response);
            }else {
                response.sendRedirect("/admin/events");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
