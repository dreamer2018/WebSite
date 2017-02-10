package org.xiyoulinux.servlet.admin;

import org.xiyoulinux.dao.TitleDAO;
import org.xiyoulinux.model.Title;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhoupan on 1/31/17.
 */
@WebServlet(name = "AdminTitleServlet", urlPatterns = "/admin/title")
public class AdminTitleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TitleDAO titleDAO = new TitleDAO();
        ArrayList<Title> titles = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            titles.add(titleDAO.getTitleById(i));
        }
        request.setAttribute("titleList",titles);
        request.getRequestDispatcher("/admin/title.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
