package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.BlogDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 11/29/16.
 */

@WebServlet(name = "AdminBlogServlet",urlPatterns = "/admin/blog")
public class AdminBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("page") == null) {
            /*
            首次进入，或未触发分页操作，则显示第一页
             */
            BlogDAO blogDAO = new BlogDAO();
            ArrayList blogList = blogDAO.getBlogByPage(1, "");
            int pageCount = blogDAO.getAllPageCount();
            int currentPage = blogDAO.getCurrentPage();
            int allCount = blogDAO.getAllCount();
            request.setAttribute("blogList", blogList);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.getRequestDispatcher("/admin/blog.jsp").forward(request, response);
        } else {
            BlogDAO blogDAO = new BlogDAO();
            ArrayList blogList = blogDAO.getBlogByPage((Integer) request.getAttribute("currentPage"), "");
            int pageCount = blogDAO.getAllPageCount();
            int currentPage = blogDAO.getCurrentPage();
            int allCount = blogDAO.getAllCount();
            request.setAttribute("blogList", blogList);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.getRequestDispatcher("/admin/blog.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
