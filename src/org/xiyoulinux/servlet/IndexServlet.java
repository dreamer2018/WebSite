package org.xiyoulinux.servlet;

import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.dao.TitleDAO;
import org.xiyoulinux.model.Blog;
import org.xiyoulinux.model.Events;
import org.xiyoulinux.model.Title;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 12/3/16.
 */
@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取标题
        TitleDAO titleDAO = new TitleDAO();
        ArrayList<Title> titles = new ArrayList<>();
        Title title1 = titleDAO.getTitleById(1);
        titles.add(title1);
        Title title2 = titleDAO.getTitleById(2);
        titles.add(title2);
        Title title3 = titleDAO.getTitleById(3);
        titles.add(title3);
        Title title4 = titleDAO.getTitleById(4);
        titles.add(title4);
        Title title5 = titleDAO.getTitleById(5);
        titles.add(title5);
        Title title6 = titleDAO.getTitleById(6);
        titles.add(title6);

        //获取文章
        BlogDAO blogDAO = new BlogDAO();
        ArrayList<Blog> blogs = blogDAO.getBlogByPage(1, "", 5);

        //获取事件
        EventsDAO eventsDAO = new EventsDAO();
        ArrayList<Events> eventss = eventsDAO.getEventsByPage(1, "", 5);


        //request中加入标题
        request.setAttribute("titleList", titles);
        //request中加入文章
        request.setAttribute("blogList", blogs);
        //request中加入事件
        request.setAttribute("eventsList", eventss);

        //响应
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
