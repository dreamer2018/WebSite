package org.xiyoulinux.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.Part;

import org.xiyoulinux.dao.EventsDAO;
import org.xiyoulinux.model.Events;

/**
 * Created by zhoupan on 11/3/16.
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet(name = "AdminEventsInsertServlet", urlPatterns = "/admin/eventsedit")
public class AdminEventsInsertServlet extends HttpServlet {

    private static final MultipartConfig config;

    static {
        config = AdminEventsInsertServlet.class.getAnnotation(MultipartConfig.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post
        if (null == request.getParameter("id")) {
            boolean error = false;
            String title = request.getParameter("title");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String address = request.getParameter("address");
            String label = request.getParameter("label");
            String mkdown = request.getParameter("markdown");
            String content = request.getParameter("content");
            String str_sign = request.getParameter("status");
            if (title == null || title.equals("")) {
                request.setAttribute("message", "标题不能为空！");
                error = true;
            } else if (date == null || date.equals("")) {
                request.setAttribute("message", "日期不能为空！");
                error = true;
            } else if (time == null || time.equals("")) {
                request.setAttribute("message", "时间不能为空！");
                error = true;
            } else if (address == null || address.equals("")) {
                request.setAttribute("message", "地址不能为空！");
                error = true;
            } else if (label == null || label.equals("")) {
                request.setAttribute("message", "标签不能为空！");
                error = true;
            } else if (mkdown == null || mkdown.equals("")) {
                request.setAttribute("message", "内容不能为空！");
                error = true;
            } else if (content == null || content.equals("")) {
                request.setAttribute("message", "内容不能为空！");
                error = true;
            }
            String filePath = "";
            try {
                Part part;
                // 接收图片:图片封装在part对象中
                part = request.getPart("poster");
                String fileName = getFileName(part);
                while (true) {
                    if (fileName.indexOf('.') < 0) {
                        break;
                    }
                    fileName = fileName.substring(fileName.indexOf('.') + 1);
                }
                // 保存图片
                Date d = new Date();
                long now = d.getTime();
                if ("".equals(fileName)) {
                    if ("0".equals(str_sign)) {
                        request.setAttribute("title", title);
                        request.setAttribute("content", content);
                        request.setAttribute("mkdown", mkdown);
                        request.setAttribute("date", date);
                        request.setAttribute("time", time);
                        request.setAttribute("address", address);
                        request.setAttribute("label", label);
                        request.setAttribute("message", "上传图片不能为空!");
                        System.out.println(title);
                        int sign = 0;
                        try {
                            sign = Integer.parseInt(str_sign);
                        } catch (NumberFormatException n) {
                            n.printStackTrace();
                        }
                        if (sign != 0) {
                            request.setAttribute("id", sign);
                        }
                        request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
                    }
                } else {
                    fileName = now + "." + fileName;
                    part.write(getServletContext().getRealPath("/upload/") + fileName);
                    filePath = "/upload/" + fileName;
                }
            } catch (Exception e) {
                if (config.maxRequestSize() == -1L || config.maxFileSize() == -1L) {
                    System.out.println("上传图片过大!");
                }
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("mkdown", mkdown);
                request.setAttribute("date", date);
                request.setAttribute("time", time);
                request.setAttribute("address", address);
                request.setAttribute("label", label);
                request.setAttribute("message", "上传图片过大(限制5M)，或存在异常!");
                System.out.println(title);
                int sign = 0;
                try {
                    sign = Integer.parseInt(str_sign);
                } catch (NumberFormatException n) {
                    n.printStackTrace();
                }
                if (sign != 0) {
                    request.setAttribute("id", sign);
                }
                request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
            }

            if (error) {
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("mkdown", mkdown);
                request.setAttribute("date", date);
                request.setAttribute("time", time);
                request.setAttribute("address", address);
                request.setAttribute("label", label);
                int sign = 0;
                try {
                    sign = Integer.parseInt(str_sign);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (sign != 0) {
                    request.setAttribute("id", sign);
                }
                request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
            } else {
                int sign = 0;
                try {
                    sign = Integer.parseInt(str_sign);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                System.out.println(sign);

                // sign为0 新添加
                if (sign == 0) {
                    Events events = new Events();
                    events.setTitle(title);
                    events.setContent(content);
                    events.setDate(date);
                    events.setTime(time);
                    events.setMarkdown(mkdown);
                    events.setPoster(filePath);
                    events.setAddress(address);
                    events.setLabel(label);
                    EventsDAO eventsDAO = new EventsDAO();
                    if (eventsDAO.insert(events)) {
                        response.sendRedirect("/admin/events");
                    } else {
                        response.sendRedirect("/404.html");
                    }
                    //对旧内容修改提交
                } else {
                    if("".equals(filePath)){
                        EventsDAO eventsDAO = new EventsDAO();
                        Events events = eventsDAO.getEventsByID(sign);
                        filePath = events.getPoster();
                    }
                    Events events = new Events();
                    events.setId(sign);
                    events.setTitle(title);
                    events.setContent(content);
                    events.setDate(date);
                    events.setTime(time);
                    events.setMarkdown(mkdown);
                    events.setPoster(filePath);
                    events.setAddress(address);
                    events.setLabel(label);
                    EventsDAO eventsDAO = new EventsDAO();
                    if (eventsDAO.update(events)) {
                        response.sendRedirect("/admin/events");
                    } else {
                        response.sendRedirect("/404.html");
                    }
                }
            }
            //get
        } else { // id不为空，表示对内容进行修改,渲染修改页面
            String str_id = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(str_id);
            } catch (NumberFormatException e) {
                response.sendRedirect("/admin/eventsedit.jsp");
            }
            EventsDAO eventsDAO = new EventsDAO();
            Events events = eventsDAO.getEventsByID(id);
            if (events == null) {
                response.sendRedirect("/admin/eventsedit.jsp");
            } else {
                request.setAttribute("id", events.getId());
                request.setAttribute("title", events.getTitle());
                request.setAttribute("content", events.getContent());
                request.setAttribute("mkdown", events.getMarkdown());
                request.setAttribute("date", events.getDate());
                request.setAttribute("time", events.getTime());
                request.setAttribute("address", events.getAddress());
                request.setAttribute("label", events.getLabel());
                request.setAttribute("poster", events.getPoster());
                request.getRequestDispatcher("/admin/eventsedit.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 从Part的Header信息中提取上传文件的文件名
     *
     * @return 上传文件的文件名，如果如果没有则返回null
     */
    private String getFileName(Part part) {
        // 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
        // IE下文件名带路径，而火狐、chrome文件名不带
        String header = part.getHeader("content-disposition");
        System.out.println("header : " + header);
        String[] params = header.split(";");
        String[] temp = params[2].split("=");
        // 获取文件名，兼容各种浏览器的写法，去掉文件名前路径和双引号
        String fileName = "";
        if (temp[1].lastIndexOf("\\") < 0)
            fileName = temp[1].substring(1, temp[1].length() - 1);
        else
            fileName = temp[1].substring(temp[1].lastIndexOf("\\") + 1, temp[1].length() - 1);
        return fileName;
    }
}
