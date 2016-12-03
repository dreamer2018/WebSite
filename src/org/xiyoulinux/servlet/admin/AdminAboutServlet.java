package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.AboutDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 11/27/16.
 */
@WebServlet(name = "AdminAboutServlet", urlPatterns = "/admin/about")
public class AdminAboutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("page") == null) {
            /*
            首次进入，或未触发分页操作，则显示第一页
             */
            AboutDAO aboutDAO = new AboutDAO();
            ArrayList aboutList = aboutDAO.getAboutByPage(1, "");
            int pageCount = aboutDAO.getAllPageCount();
            int currentPage = aboutDAO.getCurrentPage();
            int allCount = aboutDAO.getAllCount();
            request.setAttribute("aboutList", aboutList);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.getRequestDispatcher("/admin/about.jsp").forward(request, response);
        } else {
            AboutDAO aboutDAO = new AboutDAO();
            ArrayList aboutList = aboutDAO.getAboutByPage((Integer) request.getAttribute("currentPage"), "");
            int pageCount = aboutDAO.getAllPageCount();
            int currentPage = aboutDAO.getCurrentPage();
            int allCount = aboutDAO.getAllCount();
            request.setAttribute("aboutList", aboutList);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("allCount", allCount);
            request.getRequestDispatcher("/admin/about.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
