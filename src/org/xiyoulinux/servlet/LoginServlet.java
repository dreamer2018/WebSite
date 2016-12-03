package org.xiyoulinux.servlet;

import org.xiyoulinux.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhoupan on 10/19/16.
 */


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute("login", null);
        if (username.length() < 1 || password.length() < 1) {
            request.setAttribute("reason", "用户名或密码不能为空！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            UserDAO login = new UserDAO();
            if (login.check(username, password)) {
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("login", "ok");
                response.sendRedirect("/admin/");
            } else {
                request.setAttribute("reason", "用户名或密码不正确！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
