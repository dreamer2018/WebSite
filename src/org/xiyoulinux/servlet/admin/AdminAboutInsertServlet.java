package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.AboutDAO;
import org.xiyoulinux.model.About;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 11/29/16.
 */
@WebServlet(name = "AdminAboutInsertServlet", urlPatterns = "/admin/aboutedit")
public class AdminAboutInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello");
        if (null == request.getParameter("id")) {
            boolean error = false;
            String title = request.getParameter("title");
            String url = request.getParameter("url");
            String mkdown = request.getParameter("markdown");
            String content = request.getParameter("content");

            if (title == null || title.equals("")) {
                request.setAttribute("message", "标题不能为空！");
                error = true;
            } else if (mkdown == null || mkdown.equals("")) {
                request.setAttribute("message", "简介内容不能为空！");
                error = true;
            } else if (content == null || content.equals("")) {
                request.setAttribute("message", "简介内容不能为空！");
                error = true;
            }
            if (error) {
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("url", url);
                request.setAttribute("mkdown", mkdown);
                request.getRequestDispatcher("/admin/aboutedit.jsp").forward(request, response);
            } else {
                About about = new About();
                about.setTitle(title);
                about.setContent(content);
                about.setPicture_url(url);
                about.setMarkdown(mkdown);
                AboutDAO aboutDAO = new AboutDAO();
                if (aboutDAO.insert(about)) {
                    response.sendRedirect("/admin/about");
                } else {
                    response.sendRedirect("/404.html");
                }
            }
        } else {
            String str_id = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                response.sendRedirect("/admin/aboutsedit.jsp");
            }
            AboutDAO eventsDAO = new AboutDAO();
            About about = eventsDAO.getAboutByID(id);
            if (about == null) {
                response.sendRedirect("/admin/eventsedit.jsp");
            } else {
                request.setAttribute("id", about.getId());
                request.setAttribute("title", about.getTitle());
                request.setAttribute("content", about.getContent());
                request.setAttribute("mkdown", about.getMarkdown());
                request.setAttribute("url", about.getPicture_url());
                request.getRequestDispatcher("/admin/aboutedit.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
