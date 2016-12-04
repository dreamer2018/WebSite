package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.BlogDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhoupan on 11/29/16.
 */

@WebServlet(name = "AdminBlogServlet", urlPatterns = "/admin/blog")
public class AdminBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("page") == null) {
            /*
            首次进入，或未触发分页操作，则显示第一页
             */
            BlogDAO blogDAO = new BlogDAO();
            ArrayList blogList;

            if (null == request.getParameter("title")) {
                blogList = blogDAO.getBlogByPage(1, "");
            } else {
                String date = request.getParameter("date");
                String date1 = date.substring(0,10);
                String date2 = date.substring(13,23);
                SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Date start = d.parse(date1);
                    Date end = d.parse(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                blogList = blogDAO.getBlogByPage(1, request.getParameter("title"));
                request.setAttribute("title", request.getParameter("title"));
            }
            System.out.println(blogList.size());
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
            ArrayList blogList;
            String pagestring = request.getParameter("page");
            int page = Integer.parseInt(pagestring);
            if (null == request.getParameter("title")) {
                blogList = blogDAO.getBlogByPage(page, "");
            } else {
                blogList = blogDAO.getBlogByPage(page, request.getParameter("title"));
            }
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
        doPost(request, response);
    }
}
