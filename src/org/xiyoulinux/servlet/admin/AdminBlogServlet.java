package org.xiyoulinux.servlet.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.model.Blog;

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
        if (request.getParameter("page") == null) {
            /*
            首次进入，或未触发分页操作，则显示第一页
             */
            BlogDAO blogDAO = new BlogDAO();
            ArrayList blogList;
            if (null == request.getParameter("title")) {
                blogList = blogDAO.getBlogByPage(1, "");
            } else {
                blogList = blogDAO.getBlogByPage(1, request.getParameter("title"));
                request.setAttribute("title", request.getParameter("title"));
            }
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
            ArrayList<Blog> blogList;
            String pagestring = request.getParameter("page");
            int page = Integer.parseInt(pagestring);
            System.out.println(request.getParameter("title"));
            String title;
            if (null == request.getParameter("title")) {
                blogList = blogDAO.getBlogByPage(page, "");
                title = "";
            } else {
                blogList = blogDAO.getBlogByPage(page, request.getParameter("title"));
                title = request.getParameter("title");
            }
            int pageCount = blogDAO.getAllPageCount();
            int currentPage = blogDAO.getCurrentPage();
            int allCount = blogDAO.getAllCount();
            JSONObject json = new JSONObject();

            JSONArray blogs = new JSONArray();
            json.put("pageCount", pageCount);
            json.put("currPage", currentPage);
            json.put("allCount", allCount);
            json.put("title", title);
            for (Blog blog : blogList) {
                JSONObject b = new JSONObject();
                b.put("id", blog.getId());
                b.put("title", blog.getTitle());
                b.put("author", blog.getAuthor());
                b.put("date", blog.getDate());
                b.put("time", blog.getTime());
                b.put("summary", blog.getSummary());
                b.put("url", blog.getUrl());
                b.put("status", blog.getStatus());
                blogs.put(b);
            }
            json.put("blogList", blogs);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
